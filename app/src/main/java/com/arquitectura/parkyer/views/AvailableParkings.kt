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

class AvailableParkings : AppCompatActivity() {

    val user = User()
    var logIn = false
    val micro = MicroServicioAdmin2()

    private var Availables: List<Parking>? = emptyList()
    private val arrayTutorialType = object : TypeToken<List<Parking>>() {}.type
    private var suscription: Parking? = Parking()

    val atras by lazy { findViewById(R.id.atras) as Button }

    val available by lazy { findViewById(R.id.available) as ListView }

    private val compositeDisposable = CompositeDisposable()

    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_available_parkings)
        progressDialog = ProgressDialog(this)
        cargarInformacion()
        val id_client = user.id

        compositeDisposable.add(
            micro.getAvailableParkings()
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
                    Availables = Gson().fromJson(
                        JSONObject(data.get("data").toString()).get("getAvailableParkings").toString(),
                        arrayTutorialType
                    )
                    val arrayAdapter:ArrayAdapter<*>

                    val availablesarray = ArrayList<String>()

                    Availables?.forEach {
                        it.location?.let { it1 -> availablesarray.add(it1) }
                    }

                    arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,availablesarray)
                    available.adapter = arrayAdapter

                    available.setOnItemClickListener() { parent, view, position, id ->
                        //Toast.makeText(this, parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show()
                        val parking_id = Availables?.get(position)?.id
                        CreateDialog(parking_id, id_client.toString())
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

        atras.setOnClickListener {
            onBackPressed()
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

    private fun CreateDialog(id: Int?, id_client: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Suscribirse?")
        builder.setMessage("¿Deseas suscribirte?")
        //builder.setPositiveButton("OK", DialogInterface.OnClickLsistener(function = x))

        builder.setPositiveButton("Crear") { dialog, which ->

            compositeDisposable.add(
                micro.newSuscription(id, id_client)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe {
                        progressDialog.setTitle("Creando..")
                        progressDialog.setCancelable(false)
                        progressDialog.show()
                    }
                    .subscribe({
                        progressDialog.cancel()
                        val data = JSONObject(it)
                        suscription = Gson().fromJson(
                            JSONObject(
                                data.get("data").toString()
                            ).getString("newSuscription").toString(), Parking::class.java
                        )
                        Toast.makeText(
                            applicationContext,
                            "Creada", Toast.LENGTH_SHORT
                        ).show()
                        val intent = Intent(this, Perfil::class.java)
                        enviarInformacion(intent)
                        startActivity(intent)
                        finish()
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
}

