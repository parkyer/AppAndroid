package com.arquitectura.parkyer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arquitectura.parkyer.microservicios.MicroServicioContacto

class MainActivity : AppCompatActivity() {
    private val contacto =
        MicroServicioContacto()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        contacto.obtenerMensajes()
    }
}