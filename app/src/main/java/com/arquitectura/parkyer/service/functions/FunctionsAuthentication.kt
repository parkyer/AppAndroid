package com.arquitectura.parkyer.service.functions

import android.util.Log
import com.a.graphqlwithretrofit.ServiceBuilder
import com.arquitectura.parkyer.models.UserLogin
import com.arquitectura.parkyer.models.Login
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject

class FunctionsAuthentication {

    private val retrofit = ServiceBuilder.serviceBuilder

    var user: UserLogin? = UserLogin()
    var login: Login = Login()

    fun createUser(
        name: String,
        last_name: String,
        email: String,
        password: String,
        phone: Int,
        payment_method: Int,
        address: String
    ) {

        val paramObject = JSONObject()
        paramObject.put(
            "query",
            "mutation {crearUsuario(usuario:{name: ${name}, last_name: \"${last_name}\", email: \"${email}\", password: \"${password}\", phone: ${phone}, payment_method: ${payment_method}, address: \"${address}\"})" +
                    "{id,name, last_name, email, password, phone, payment_method, address}}"
        )

        GlobalScope.launch {
            try {
                val response = retrofit.sendRequest(paramObject.toString())
                val data = JSONObject(response.body().toString())
                user = Gson().fromJson(
                    JSONObject(data.get("data").toString()).get("crearUsuario").toString(),
                    UserLogin::class.java
                )
                Log.e("response", user.toString())
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }

    fun login(email: String, password: String): Login {

        val paramObject = JSONObject()
        paramObject.put(
            "query",
            "mutation {iniciarSesion(login:{email: \"${email}\", password: \"${password}\"}){access, id}}"
        )

        GlobalScope.launch {
            try {
                val response = retrofit.sendRequest(paramObject.toString())
                Log.e("response", response.body().toString())
                val data = JSONObject(response.body().toString())
                val log = Gson().fromJson(
                    JSONObject(data.get("data").toString()).get("iniciarSesion").toString(),
                    Login::class.java
                )
                login.id = log.id
                login.name = log.name
                Log.e("response", login.toString())
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
        return login
    }

}