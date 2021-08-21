package com.arquitectura.parkyer.views

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.arquitectura.parkyer.MainActivity
import com.arquitectura.parkyer.R
import com.arquitectura.parkyer.microservicios.MicroServicioPerfil
import com.arquitectura.parkyer.models.Login
import com.arquitectura.parkyer.models.User
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject

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
    val editar by lazy { findViewById(R.id.editar) as Button }

    val micro = MicroServicioPerfil()

    private val compositeDisposable = CompositeDisposable()

    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_perfil)
        progressDialog = ProgressDialog(this)
        cargarInformacion()
        nombre.getText().insert(nombre.getSelectionStart(), user.name);
        apellido.getText().insert(apellido.getSelectionStart(), user.lastName);
        correo.getText().insert(correo.getSelectionStart(), user.email);
        telefono.getText().insert(telefono.getSelectionStart(), user.phone.toString());
        direccion.getText().insert(direccion.getSelectionStart(), user.address);
        cancelar.setOnClickListener {
            onBackPressed()
        }
        editar.setOnClickListener {
            ingresarNuevoPerfil()
        }
    }

    fun ingresarNuevoPerfil() {
        user.name = nombre.text.toString()
        user.address = direccion.text.toString()
        user.lastName = apellido.text.toString()
        user.email = correo.text.toString()
        user.phone = telefono.text.toString().toInt()
        compositeDisposable.add(
            micro.editarUsuario(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    progressDialog.setTitle("Editando Información")
                    progressDialog.setCancelable(false)
                    progressDialog.show()
                }
                .subscribe({
                    progressDialog.cancel()
                    val data = JSONObject(it)
                    Log.d("response", data.toString())
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }, {
                    progressDialog.cancel()
                })
        )
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