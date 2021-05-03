package com.arquitectura.parkyer.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.arquitectura.parkyer.R
import com.arquitectura.parkyer.models.User

class Perfil : AppCompatActivity() {
    //globales
    val user = User()
    var logIn = false

    //Botones
    val editar by lazy { findViewById(R.id.editar) as Button }
    val cambiarConstrasenia by lazy { findViewById(R.id.cambiar_contrasenia) as Button }
    val campiarMetodo by lazy { findViewById(R.id.cambiar_metodo) as Button }
    val eliminar by lazy { findViewById(R.id.eliminar_cuenta) as Button }

    //Botones Menu
    val propietario by lazy { findViewById(R.id.propietario) as Button }
    val perfil by lazy { findViewById(R.id.perfil) as Button }
    val arrendador by lazy { findViewById(R.id.arrendador) as Button }

    //Textos
    val nombre by lazy { findViewById(R.id.nombre) as TextView }
    val apellido by lazy { findViewById(R.id.apellido) as TextView }
    val correo by lazy { findViewById(R.id.correo) as TextView }
    val telefono by lazy { findViewById(R.id.telefono) as TextView }
    val direccion by lazy { findViewById(R.id.direccion) as TextView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)
        cargarInformacion()
        editar.setOnClickListener {
            nombre.text = user.name
            apellido.text = user.lastName
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