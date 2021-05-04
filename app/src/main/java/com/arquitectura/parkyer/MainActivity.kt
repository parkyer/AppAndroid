package com.arquitectura.parkyer

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.arquitectura.parkyer.microservicios.MicroServicioPerfil
import com.arquitectura.parkyer.models.User
import com.arquitectura.parkyer.views.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private val perfil =
        MicroServicioPerfil(this)

    val user = User()
    val logIn = false

    //Botones
    val iniciar by lazy { findViewById(R.id.iniciar_sesion) as Button }
    val contacto by lazy { findViewById(R.id.contacto) as Button }
    val quienes by lazy { findViewById(R.id.quienes) as Button }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iniciar.setOnClickListener {
            //Agregué Iniciar Sesión
            //val intent = Intent(this, ContactoInicio::class.java)
            //enviarInformacion(intent)
            //startActivity(intent)
        }
        contacto.setOnClickListener {
            val intent = Intent(this, Perfil::class.java)
            enviarInformacion(intent)
            startActivity(intent)
        }
        quienes.setOnClickListener {
            val intent = Intent(this, QuienesSomos::class.java)
            enviarInformacion(intent)
            startActivity(intent)
        }
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