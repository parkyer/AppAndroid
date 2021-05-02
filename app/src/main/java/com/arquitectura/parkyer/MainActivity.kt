package com.arquitectura.parkyer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arquitectura.parkyer.microservicios.MicroServicioPerfil

class MainActivity : AppCompatActivity() {
    private val perfil =
        MicroServicioPerfil()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        perfil.obtenerUsuario(17)
    }
}