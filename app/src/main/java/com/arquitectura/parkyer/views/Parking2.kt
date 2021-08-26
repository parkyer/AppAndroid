package com.arquitectura.parkyer.views

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import com.arquitectura.parkyer.R
import com.arquitectura.parkyer.microservicios.MicroServicioAdmin1
import com.arquitectura.parkyer.models.Parking
import com.arquitectura.parkyer.models.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject

class Parking2 : AppCompatActivity() {

    val user = User()
    var logIn = false
    val micro = MicroServicioAdmin1()

    val new by lazy { findViewById(R.id.newparking) as Button }
    val atras by lazy { findViewById(R.id.atras) as Button }
    val parkings by lazy { findViewById(R.id.parkings) as ListView }

    private val arrayTutorialType = object : TypeToken<List<Parking>>() {}.type
    private var Parkings= ArrayList<Parking>()
    private var parkings_size: Int? = 0
    var parkingOwnerArray = ArrayList<Parking>()

    private val compositeDisposable = CompositeDisposable()

    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parking2)
        progressDialog = ProgressDialog(this)
        cargarInformacion()

        compositeDisposable.add(
            micro.getParkinsCreated()
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
                    Parkings = Gson().fromJson(
                        JSONObject(
                            data.get("data").toString()
                        ).getString("getParkingsCreated").toString(), arrayTutorialType
                    )
                    val arrayAdapter: ArrayAdapter<*>

                    val parkingsarray = ArrayList<String>()
                    parkings_size = Parkings?.size
                    Log.d("response", Parkings.toString())
                    Parkings?.forEach {
                        if(it.id_owner == user.id){
                            parkingOwnerArray.add(it)
                            it.location?.let { it1 -> parkingsarray.add(it1) }
                        }
                    }

                    arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,parkingsarray)
                    parkings.adapter = arrayAdapter

                    parkings.setOnItemClickListener() { parent, view, position, id ->
                        val parking_id = parkingOwnerArray.get(position).id
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
            val intent = Intent(this, NewParking::class.java)
            enviarInformacion(intent)
            startActivity(intent)
        }
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
        intent.putExtra("list_parkings",Parkings)
    }

    private fun DeleteDialog(id: Int?) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Eliminar Parqueadero?")
        builder.setMessage("¿Deseas eliminar tu parqueadero?")
        //builder.setPositiveButton("OK", DialogInterface.OnClickLsistener(function = x))

        builder.setPositiveButton("Eliminar") { dialog, which ->

            compositeDisposable.add(
                micro.deleteParkingById(id)
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
                        Log.d("response", data.toString())
                        Toast.makeText(
                            applicationContext,
                            "Eliminado", Toast.LENGTH_SHORT
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
}