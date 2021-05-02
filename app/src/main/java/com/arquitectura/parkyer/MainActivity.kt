package com.arquitectura.parkyer

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arquitectura.parkyer.microservicios.MicroServicioPerfil
import com.arquitectura.parkyer.views.Contacto

class MainActivity : AppCompatActivity() {
    private val perfil =
        MicroServicioPerfil()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        perfil.obtenerUsuario(17)
    }
}