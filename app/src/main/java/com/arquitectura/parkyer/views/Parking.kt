package com.arquitectura.parkyer.views

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.arquitectura.parkyer.R
import com.arquitectura.parkyer.microservicios.MicroServicioAdmin2
import com.arquitectura.parkyer.models.Parking
import com.arquitectura.parkyer.models.User

class Parking : AppCompatActivity() {

    val user = User()
    var logIn = false
    val parking = Parking()
    val functions = MicroServicioAdmin2()

    val en_uso by lazy { findViewById(R.id.en_uso) as ListView }
    val disponibles by lazy { findViewById(R.id.disponibles) as ListView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parking)
        cargarInformacion()
        en_uso.setOnClickListener {
            functions.getParkingsUsedBy(user.id!!)
        }
        disponibles.setOnClickListener {
            functions.getAvailableParkings()
        }
    }

    fun cargarInformacion() {
        user.id = intent.getIntExtra("id", 0)
        user.name = intent.getStringExtra("name")
        user.lastName = intent.getStringExtra("lastName")
        user.email = intent.getStringExtra("email")
        user.password = intent.getStringExtra("password")
        user.phone = intent.getIntExtra("phone", 0)
        user.paymentMethod = intent.getIntExtra("paymentMethod", 0)
        user.address = intent.getStringExtra("address")
        logIn = intent.getBooleanExtra("logIn", false)
    }
}

