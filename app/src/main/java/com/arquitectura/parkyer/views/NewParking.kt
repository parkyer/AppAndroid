package com.arquitectura.parkyer.views

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.arquitectura.parkyer.MainActivity
import com.arquitectura.parkyer.R
import com.arquitectura.parkyer.microservicios.MicroServicioAdmin1
import com.arquitectura.parkyer.models.User
import com.arquitectura.parkyer.models.Parking
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject

class NewParking : AppCompatActivity() {
    //globales
    val user = User()
    var logIn = false
    private var parkings_size: Int? = 0
    val parking = Parking()
    var parkingOwnerArray = ArrayList<Parking>()

    //Textos
    val latitude by lazy { findViewById(R.id.latitude) as EditText }
    val longitude by lazy { findViewById(R.id.longitude) as EditText }
    val location by lazy { findViewById(R.id.location) as EditText }
    val type by lazy { findViewById(R.id.type) as EditText }

    val cancelar by lazy { findViewById(R.id.cancel) as Button }
    val editar by lazy { findViewById(R.id.editar) as Button }

    val micro = MicroServicioAdmin1()

    private val compositeDisposable = CompositeDisposable()

    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_parking)
        progressDialog = ProgressDialog(this)
        cancelar.setOnClickListener {
            onBackPressed()
        }
        editar.setOnClickListener {
            CreateParking()
        }
    }

    fun CreateParking() {
        cargarInformacion()
        Log.d("response", parkingOwnerArray.toString())
        var i = 0
        var idSalida = 0
        var definitiva = 0
        parkingOwnerArray.forEach {
            Log.d("response", "Parking: " +i + " " + idSalida + "______" + it.id)
            if (it.id == i) {
                i++
            } else {
                var bandera = false
                idSalida = i
                parkingOwnerArray.forEach {
                    if(idSalida==it.id){
                        bandera = true
                    }
                }
                if(bandera == false){
                    definitiva = idSalida
                }else{
                    i++
                }
            }
        }
        if(definitiva == 0){
            definitiva = parkingOwnerArray.size + 1
        }
        parking.id = definitiva
        parking.id_owner = user.id
        parking.id_client = null
        parking.latitude = latitude.text.toString()
        parking.longitude = longitude.text.toString()
        parking.location = location.text.toString()
        parking.type = type.text.toString()
        compositeDisposable.add(
            micro.createParking(parking)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    progressDialog.setTitle("Creando Parqueadero")
                    progressDialog.setCancelable(false)
                    progressDialog.show()
                }
                .subscribe({
                    progressDialog.cancel()
                    val data = JSONObject(it)
                    Log.d("response", data.toString())
                    val intent = Intent(this, Perfil::class.java)
                    enviarInformacion(intent)
                    startActivity(intent)
                    finish()
                }, {
                    progressDialog.cancel()
                })
        )
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
        parkingOwnerArray = intent.getSerializableExtra("list_parkings") as ArrayList<Parking>
    }
}