package com.arquitectura.parkyer.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.arquitectura.parkyer.R
import com.arquitectura.parkyer.models.User

class EditarPerfil : AppCompatActivity() {

    //globales
    val user = User()
    var logIn = false

    //Textos
    val nombre by lazy { findViewById(R.id.nombre) as EditText }
    val apellido by lazy { findViewById(R.id.apellido) as EditText }
    val correo by lazy { findViewById(R.id.correo) as EditText }
    val telefono by lazy { findViewById(R.id.telefono) as EditText }
    val direccion by lazy { findViewById(R.id.direccion) as EditText }

    val cancelar by lazy { findViewById(R.id.eliminar_cuenta) as Button }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_perfil)
        cargarInformacion()
        nombre.getText().insert(nombre.getSelectionStart(), user.name);
        apellido.getText().insert(apellido.getSelectionStart(), user.lastName);
        correo.getText().insert(correo.getSelectionStart(), user.email);
        telefono.getText().insert(telefono.getSelectionStart(), user.phone.toString());
        direccion.getText().insert(direccion.getSelectionStart(), user.address);
        cancelar.setOnClickListener {
            onBackPressed()
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