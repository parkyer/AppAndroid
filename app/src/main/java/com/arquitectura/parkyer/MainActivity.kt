package com.arquitectura.parkyer

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arquitectura.parkyer.microservicios.MicroServicioContacto
import com.arquitectura.parkyer.views.Contacto

class MainActivity : AppCompatActivity() {
    private val contacto =
        MicroServicioContacto()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //val intent = Intent(this,Contacto::class.java)
        //startActivity(intent)
    }
}