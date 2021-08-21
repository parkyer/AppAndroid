package com.arquitectura.parkyer.views

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.arquitectura.parkyer.R
import com.arquitectura.parkyer.microservicios.MicroServicioAdmin2
import com.arquitectura.parkyer.models.Login
import com.arquitectura.parkyer.models.Parking
import com.arquitectura.parkyer.models.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject

class Parking : AppCompatActivity() {

    val user = User()
    var logIn = false
    //val parking = Parking()
    val micro = MicroServicioAdmin2()

    val new by lazy { findViewById(R.id.newsubscription) as Button }

    private var UsedBy: List<Parking>? = emptyList()
    private val arrayTutorialType = object : TypeToken<List<Parking>>() {}.type
    private var unsuscription: Parking? = Parking()

    val usedby by lazy { findViewById(R.id.usedby) as ListView }

    private val compositeDisposable = CompositeDisposable()

    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parking)
        progressDialog = ProgressDialog(this)
        cargarInformacion()
        val id_client = user.id

        compositeDisposable.add(
                micro.getParkingsUsedBy(id_client)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe {
                            progressDialog.setTitle("Cargando Parkings")
                            progressDialog.setCancelable(false)
                            progressDialog.show()
                        }
                        .subscribe({
                            progressDialog.cancel()
                            val data = JSONObject(it)
                            UsedBy = Gson().fromJson(
                                    JSONObject(data.get("data").toString()).get("getParkingsUsedBy").toString(),
                                    arrayTutorialType
                            )
                            val arrayAdapter:ArrayAdapter<*>

                            val usedbyarray = ArrayList<String>()

                            UsedBy?.forEach {
                                it.location?.let { it1 -> usedbyarray.add(it1) }
                            }

                            arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,usedbyarray)
                            usedby.adapter = arrayAdapter

                            usedby.setOnItemClickListener() { parent, view, position, id ->
                                //Toast.makeText(this, parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show()
                                val parking_id = UsedBy?.get(position)?.id
                                DeleteDialog(parking_id)
                                arrayAdapter.notifyDataSetChanged()
                            }
                        }, {
                            progressDialog.cancel()
                            Toast.makeText(
                                    applicationContext,
                                    "ERROR", Toast.LENGTH_SHORT
                            ).show()
                        })
        )
        new.setOnClickListener {
            val intent = Intent(this, AvailableParkings::class.java)
            enviarInformacion(intent)
            startActivity(intent)
        }
    }

    fun cargarInformacion() {
        user.id = intent.getIntExtra("id", 0)
        user.name = intent.getStringExtra("name")
        user.lastName = intent.getStringExtra("lastName")
        user.email = intent.getStringExtra("email")
        user.password = intent.getStringExtra("password")
        user.phone = intent.getIntExtra("phone", 0)
        user.paymentMethod = intent.getIntExtra("paymentMethod", 0)
        user.address = intent.getStringExtra("address")
        logIn = intent.getBooleanExtra("logIn", false)
    }

    fun enviarInformacion(intent: Intent) {
        intent.putExtra("id", user.id)
        intent.putExtra("name", user.name)
        intent.putExtra("lastName", user.lastName)
        intent.putExtra("email", user.email)
        intent.putExtra("password", user.password)
        intent.putExtra("phone", user.phone)
        intent.putExtra("paymentMethod", user.paymentMethod)
        intent.putExtra("address", user.address)
        intent.putExtra("logIn", logIn)
    }

    private fun DeleteDialog(id: Int?) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Eliminar Suscripción?")
        builder.setMessage("¿Deseas eliminar tu suscripción?")
        //builder.setPositiveButton("OK", DialogInterface.OnClickLsistener(function = x))

        builder.setPositiveButton("Eliminar") { dialog, which ->

            compositeDisposable.add(
                    micro.deleteSuscription(id)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnSubscribe {
                                progressDialog.setTitle("Eliminando..")
                                progressDialog.setCancelable(false)
                                progressDialog.show()
                            }
                            .subscribe({
                                progressDialog.cancel()
                                val data = JSONObject(it)
                                unsuscription = Gson().fromJson(
                                        JSONObject(
                                                data.get("data").toString()
                                        ).getString("deleteSuscription").toString(), Parking::class.java
                                )
                                Toast.makeText(
                                        applicationContext,
                                        "Eliminado", Toast.LENGTH_SHORT
                                ).show()
                            }, {
                                progressDialog.cancel()
                                Toast.makeText(
                                        applicationContext,
                                        "ERROR", Toast.LENGTH_SHORT
                                ).show()
                            })
            )
        }

        builder.setNegativeButton("Cancelar") { dialog, which ->

        }
        builder.show()
    }
}

