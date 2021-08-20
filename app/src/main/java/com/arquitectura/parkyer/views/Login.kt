package com.arquitectura.parkyer.views

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.arquitectura.parkyer.R
import com.arquitectura.parkyer.microservicios.MicroServicioAuthentication
import com.arquitectura.parkyer.microservicios.MicroServicioPerfil
import com.arquitectura.parkyer.models.Login
import com.arquitectura.parkyer.models.User
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject


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

    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        cargarInformacionLogin()
        progressDialog = ProgressDialog(this)
        cancelar.setOnClickListener {
            onBackPressed()
        }
        login.setOnClickListener {
            compositeDisposable.add(
                micro.Login(email.text.toString(), password.text.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe {
                        progressDialog.setTitle("Iniciando Sesión")
                        progressDialog.setCancelable(false)
                        progressDialog.show()
                    }
                    .subscribe({
                        progressDialog.cancel()
                        val data = JSONObject(it)
                        Log.d("response", data.toString())
                        val log = Gson().fromJson(
                            JSONObject(data.get("data").toString()).get("iniciarSesion").toString(),
                            Login::class.java
                        )
                        verificarLogIn(Login(log.name, log.id))
                    }, {
                        progressDialog.cancel()
                        verificarLogIn(Login(name = "Denied"))
                    })
            )
        }
    }

    fun verificarLogIn(login: Login) {
        if (login.name != "Denied") {
            login.id?.let { id ->
                compositeDisposable.add(
                    microPerfil.obtenerUsuario(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe {
                            progressDialog.setTitle("Cargando Datos")
                            progressDialog.setCancelable(false)
                            progressDialog.show()
                        }
                        .subscribe({
                            progressDialog.cancel()
                            val data = JSONObject(it)
                            Log.d("response", data.toString())
                            val getUser = JSONObject(data.get("data").toString())
                            val userJson = JSONObject(getUser.get("getUser").toString())
                            val usuario = Gson().fromJson(userJson.toString(), User::class.java)
                            user.name = usuario.name
                            user.lastName = usuario.lastName
                            user.id = usuario.id
                            user.address = usuario.address
                            user.paymentMethod = usuario.paymentMethod
                            user.email = usuario.email
                            user.phone = usuario.phone
                            user.password = usuario.password
                            logInGlobal = true
                            val intent = Intent(this, Perfil::class.java)
                            enviarInformacionLogin(intent)
                            startActivity(intent)
                        }, {
                            progressDialog.cancel()
                        })
                )
            }
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