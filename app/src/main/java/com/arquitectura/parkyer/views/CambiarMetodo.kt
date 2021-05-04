package com.arquitectura.parkyer.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.arquitectura.parkyer.R

class CambiarMetodo : AppCompatActivity() {

    val cancelar by lazy { findViewById(R.id.eliminar_cuenta) as Button }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cambiar_metodo)
        cancelar.setOnClickListener {
            onBackPressed()
        }
    }
}