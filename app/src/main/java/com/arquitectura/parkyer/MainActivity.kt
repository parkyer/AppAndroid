package com.arquitectura.parkyer

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arquitectura.parkyer.microservicios.MicroServicioPerfil
import com.arquitectura.parkyer.models.User
import com.arquitectura.parkyer.views.Contacto
import com.arquitectura.parkyer.views.EditarPerfil
import com.arquitectura.parkyer.views.Perfil
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private val perfil =
        MicroServicioPerfil(this)

    val user = User()
    val logIn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(this, EditarPerfil::class.java)
        enviarInformacion(intent)
        startActivity(intent)
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