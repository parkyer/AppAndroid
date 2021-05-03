package com.arquitectura.parkyer.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.arquitectura.parkyer.MainActivity
import com.arquitectura.parkyer.R
import com.arquitectura.parkyer.microservicios.MicroServicioContacto

class Contacto : AppCompatActivity() {
    val micro = MicroServicioContacto()
    val button by lazy { this.findViewById<Button>(R.id.button) }
    val text by lazy { this.findViewById<TextView>(R.id.nombre) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacto)
        button.setOnClickListener {
            buscar()
        }
    }

    fun buscar() {
        var nombre = text.text.toString()
        Log.d("prueba", nombre)
    }
}