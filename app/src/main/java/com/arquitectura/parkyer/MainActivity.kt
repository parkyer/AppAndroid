package com.arquitectura.parkyer

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arquitectura.parkyer.microservicios.MicroServicioPerfil
import com.arquitectura.parkyer.models.User
import com.arquitectura.parkyer.views.Contacto
import com.arquitectura.parkyer.views.Perfil
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private val perfil =
        MicroServicioPerfil(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(this, Perfil::class.java)
        startActivity(intent)
    }
}