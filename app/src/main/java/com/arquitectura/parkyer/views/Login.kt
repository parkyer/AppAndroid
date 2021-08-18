package com.arquitectura.parkyer.views

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.arquitectura.parkyer.R
import com.arquitectura.parkyer.microservicios.MicroServicioAuthentication
import com.arquitectura.parkyer.microservicios.MicroServicioPerfil
import com.arquitectura.parkyer.models.Login
import com.arquitectura.parkyer.models.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


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

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        cargarInformacionLogin()
        cancelar.setOnClickListener {
            onBackPressed()
        }
        login.setOnClickListener {
            compositeDisposable.add(
                micro.Login(email.text.toString(), password.text.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        verificarLogIn(it)
                    }, {
                        verificarLogIn(Login(name = "Denied"))
                    })
            )
        }
    }

    fun verificarLogIn(login: Login) {
        if(login.name == "Wait"){
            user = User()
            Toast.makeText(
                applicationContext,
                "Servidor No Responde", Toast.LENGTH_SHORT
            ).show()
        }
        else if (login.name != "Denied") {
            login.id?.let { it1 -> microPerfil.obtenerUsuario(it1) }
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