package com.arquitectura.parkyer.views

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.arquitectura.parkyer.R
import com.arquitectura.parkyer.microservicios.MicroServicioAdmin2
import com.arquitectura.parkyer.models.Parking
import com.arquitectura.parkyer.models.User

class Parking : AppCompatActivity() {

    val user = User()
    var logIn = false
    //val parking = Parking()
    //val functions = MicroServicioAdmin2()

    val usedby by lazy { findViewById(R.id.usedby) as ListView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parking)
        cargarInformacion()
        val id_client = user.id

        val arrayAdapter:ArrayAdapter<*>
        val persona_1 = "Carlos"
        val persona_2 = "Santiago"
        val personas = mutableListOf(persona_1, persona_2, id_client)

        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,personas)
        usedby.adapter = arrayAdapter

        usedby.setOnItemClickListener(){parent,view,position,id->

            Toast.makeText(this,parent.getItemAtPosition(position).toString(),Toast.LENGTH_LONG)

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