package com.arquitectura.parkyer.views

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.arquitectura.parkyer.MainActivity
import com.arquitectura.parkyer.R
import com.arquitectura.parkyer.microservicios.MicroServicioPerfil
import com.arquitectura.parkyer.models.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject

class CambiarContrasenia : AppCompatActivity() {

    //globales
    val user = User()
    var logIn = false

    val cancelar by lazy { findViewById(R.id.eliminar_cuenta) as Button }
    val aceptar by lazy { findViewById(R.id.editar) as Button }

    val nombre by lazy { findViewById(R.id.nombre) as EditText }
    val apellido by lazy { findViewById(R.id.apellido) as EditText }

    val micro = MicroServicioPerfil()

    private val compositeDisposable = CompositeDisposable()

    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cambiar_contrasenia)
        progressDialog = ProgressDialog(this)
        cargarInformacion()
        cancelar.setOnClickListener {
            onBackPressed()
        }
        aceptar.setOnClickListener {
            cambiar()
        }
    }

    fun cambiar(){
        if(nombre.text.toString() == apellido.text.toString()){
            user.password = nombre.text.toString()
            compositeDisposable.add(
                micro.cambiarContrasenia(user)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe {
                        progressDialog.setTitle("Cambiando Contraseña")
                        progressDialog.setCancelable(false)
                        progressDialog.show()
                    }
                    .subscribe({
                        progressDialog.cancel()
                        val data = JSONObject(it)
                        Log.d("response", data.toString())
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }, {
                        progressDialog.cancel()
                    })
            )
        }else{
            Toast.makeText(
                applicationContext,
                "Contraseñas No Coinciden", Toast.LENGTH_SHORT
            ).show()
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
}