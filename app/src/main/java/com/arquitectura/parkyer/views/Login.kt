package com.arquitectura.parkyer.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.arquitectura.parkyer.R
import com.arquitectura.parkyer.models.UserLogin

class Login : AppCompatActivity() {
    //globales
    val userlog = UserLogin()
    var logIn = false

    //Botones
    val login by lazy { findViewById(R.id.editar) as Button }
    val cancelar by lazy { findViewById(R.id.eliminar_cuenta) as Button }

    //Textos
    val email by lazy { findViewById(R.id.email) as TextView }
    val password by lazy { findViewById(R.id.password) as TextView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        cargarInformacionLogin()
        login.setOnClickListener {
            email.text = userlog.email
            password.text = userlog.password
        }
    }

    fun cargarInformacionLogin() {
        userlog.id = intent.getIntExtra("id", 0)
        userlog.name = intent.getStringExtra("name")
        userlog.last_name = intent.getStringExtra("last_name")
        userlog.email = intent.getStringExtra("email")
        userlog.password = intent.getStringExtra("password")
        userlog.phone = intent.getIntExtra("phone", 0)
        userlog.payment_method = intent.getIntExtra("payment_method", 0)
        userlog.address = intent.getStringExtra("address")
        logIn = intent.getBooleanExtra("logIn", true)
    }

    fun enviarInformacionLogin(intent: Intent) {
        intent.putExtra("id", userlog.id)
        intent.putExtra("name", userlog.name)
        intent.putExtra("lastName", userlog.last_name)
        intent.putExtra("email", userlog.email)
        intent.putExtra("password", userlog.password)
        intent.putExtra("phone", userlog.phone)
        intent.putExtra("paymentMethod", userlog.payment_method)
        intent.putExtra("address", userlog.address)
        intent.putExtra("logIn", logIn)
    }
}