package com.arquitectura.parkyer.views

import android.app.AlertDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.arquitectura.parkyer.MainActivity
import com.arquitectura.parkyer.R
import com.arquitectura.parkyer.microservicios.MicroServicioPerfil
import com.arquitectura.parkyer.models.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject

class Perfil : AppCompatActivity() {

    //globales
    val user = User()
    var logIn = false

    //Botones
    val editar by lazy { findViewById(R.id.editar) as Button }
    val cambiarConstrasenia by lazy { findViewById(R.id.cambiar_contrasenia) as Button }
    val campiarMetodo by lazy { findViewById(R.id.cambiar_metodo) as Button }
    val eliminar by lazy { findViewById(R.id.eliminar_cuenta) as Button }
    val subscriptions by lazy { findViewById(R.id.subscriptions) as Button }
    val parkings by lazy { findViewById(R.id.parkings) as Button }
    val volver by lazy { findViewById(R.id.volver) as Button }

    //Textos
    val nombre by lazy { findViewById(R.id.nombre) as TextView }
    val apellido by lazy { findViewById(R.id.apellido) as TextView }
    val correo by lazy { findViewById(R.id.correo) as TextView }
    val telefono by lazy { findViewById(R.id.telefono) as TextView }
    val direccion by lazy { findViewById(R.id.direccion) as TextView }

    val micro = MicroServicioPerfil()

    private val compositeDisposable = CompositeDisposable()

    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)
        cargarInformacion()
        nombre.text = user.name
        apellido.text = user.lastName
        correo.text = user.email
        telefono.text = user.phone.toString()
        direccion.text = user.address
        editar.setOnClickListener {
            val intent = Intent(this, EditarPerfil::class.java)
            enviarInformacion(intent)
            startActivity(intent)
        }
        cambiarConstrasenia.setOnClickListener {
            val intent = Intent(this, CambiarContrasenia::class.java)
            enviarInformacion(intent)
            startActivity(intent)
        }
        campiarMetodo.setOnClickListener {
            val intent = Intent(this, CambiarMetodo::class.java)
            enviarInformacion(intent)
            startActivity(intent)
        }
        eliminar.setOnClickListener {
            showDialog()
        }
        subscriptions.setOnClickListener {
            val intent = Intent(this, Parking::class.java)
            enviarInformacion(intent)
            startActivity(intent)
        }
        parkings.setOnClickListener {
            val intent = Intent(this, Parking2::class.java)
            enviarInformacion(intent)
            startActivity(intent)
        }
        volver.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Eliminar Cuenta?")
        builder.setMessage("¿Deseas eliminar tu cuenta de manera permanente?")

        builder.setPositiveButton("Eliminar") { dialog, which ->
            compositeDisposable.add(
                micro.borrarUsuario(user)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe {
                        progressDialog.setTitle("Eliminando")
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
            Toast.makeText(
                applicationContext,
                "Eliminado", Toast.LENGTH_SHORT
            ).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        builder.setNegativeButton("Cancelar") { dialog, which ->

        }
        builder.show()
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

}