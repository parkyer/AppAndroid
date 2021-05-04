package com.arquitectura.parkyer.views

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.arquitectura.parkyer.R
import com.arquitectura.parkyer.microservicios.MicroServicioAuthentication
import com.arquitectura.parkyer.microservicios.MicroServicioPerfil
import com.arquitectura.parkyer.models.User


class Login : AppCompatActivity() {
    //globales
    var logInGlobal = false
    var user = User()

    //Botones
    val login by lazy { findViewById(R.id.editar) as Button }
    val cancelar by lazy { findViewById(R.id.eliminar_cuenta) as Button }

    //Textos
    val email by lazy { findViewById(R.id.email) as EditText }
    val password by lazy { findViewById(R.id.password) as EditText }

    val micro = MicroServicioAuthentication()
    val microPerfil = MicroServicioPerfil()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        cargarInformacionLogin()
        cancelar.setOnClickListener {
            onBackPressed()
        }
        login.setOnClickListener {
            val logIn = micro.Login(email.text.toString(), password.text.toString())
            if (logIn.id == 0) {
                microPerfil.obtenerUsuario(1)
                user.name = microPerfil.user.name
                user.lastName = microPerfil.user.lastName
                user.id = microPerfil.user.id
                user.address = microPerfil.user.address
                user.paymentMethod = microPerfil.user.paymentMethod
                user.email = microPerfil.user.email
                user.phone = microPerfil.user.phone
                user.password = microPerfil.user.password
                logInGlobal = true
                var f = true
                val intent = Intent(this, Perfil::class.java)
                enviarInformacionLogin(intent)
                startActivity(intent)
            } else {
                user = User()
                Toast.makeText(
                    applicationContext,
                    "Usuario o Contraseña Incorrecto", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun cargarInformacionLogin() {
        user.id = intent.getIntExtra("id", 0)
        user.name = intent.getStringExtra("name")
        user.lastName = intent.getStringExtra("lastName")
        user.email = intent.getStringExtra("email")
        user.password = intent.getStringExtra("password")
        user.phone = intent.getIntExtra("phone", 0)
        user.paymentMethod = intent.getIntExtra("paymentMethod", 0)
        user.address = intent.getStringExtra("address")
        logInGlobal = intent.getBooleanExtra("logIn", false)
    }

    fun enviarInformacionLogin(intent: Intent) {
        intent.putExtra("id", user.id)
        intent.putExtra("name", user.name)
        intent.putExtra("lastName", user.lastName)
        intent.putExtra("email", user.email)
        intent.putExtra("password", user.password)
        intent.putExtra("phone", user.phone)
        intent.putExtra("paymentMethod", user.paymentMethod)
        intent.putExtra("address", user.address)
        intent.putExtra("logIn", logInGlobal)
    }
}