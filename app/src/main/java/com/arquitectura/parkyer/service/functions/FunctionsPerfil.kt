package com.arquitectura.parkyer.service.functions

import android.util.Log
import com.a.graphqlwithretrofit.ServiceBuilder
import com.arquitectura.parkyer.models.Respuesta
import com.arquitectura.parkyer.models.User
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject

class FunctionsPerfil {

    private val retrofit = ServiceBuilder.serviceBuilder
    var userSalida =  User()

    fun obtenerUsuario(id: Int): User {

        val paramObject = JSONObject()
        paramObject.put("query", "query{ getUser(id: ${id}) {id, name, last_name, email,address,password,payment_method,phone}}")

        GlobalScope.launch {
            try {
                val response = retrofit.sendRequest(paramObject.toString())
                val data = JSONObject(response.body().toString())
                val getUser = JSONObject(data.get("data").toString())
                val user = JSONObject(getUser.get("getUser").toString())
                val usuario = Gson().fromJson(user.toString(), User::class.java)
                userSalida.name = usuario.name
                userSalida.lastName = usuario.lastName
                userSalida.id = usuario.id
                userSalida.address = usuario.address
                userSalida.paymentMethod = usuario.paymentMethod
                userSalida.email = usuario.email
                userSalida.phone = usuario.phone
                userSalida.password = usuario.password
                Log.e("response", getUser.get("getUser").toString())
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
        return userSalida
    }

    fun editarUsuario(user: User) {

        val paramObject = JSONObject()
        paramObject.put(
            "query",
            "mutation { updateUser(id: ${user.id}, user: {name: \"${user.name}\", last_name: \"${user.lastName}\", email: \"${user.email}\", phone: ${user.phone}, address: \"${user.address}\"}) {id,name,last_name,email,password,phone,payment_method,address}}"
        )

        GlobalScope.launch {
            try {
                val response = retrofit.sendRequest(paramObject.toString())
                Log.e("response", response.body().toString())
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }

    fun cambiarContrasenia(user: User) {

        val paramObject = JSONObject()
        paramObject.put(
            "query",
            "mutation { changePassword(id: ${user.id}, password: {password: \"${user.password}\"}){id,name,last_name,email,password,phone,payment_method,address}}"
        )

        GlobalScope.launch {
            try {
                val response = retrofit.sendRequest(paramObject.toString())
                Log.e("response", response.body().toString())
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }

    fun agregarMetodoDePago(user: User) {

        val paramObject = JSONObject()
        paramObject.put(
            "query",
            "mutation { addPaymentMethod(id: ${user.id}, payment: {payment_method: \"${user.paymentMethod}\"}){id,name,last_name,email,password,phone,payment_method,address}}"
        )

        GlobalScope.launch {
            try {
                val response = retrofit.sendRequest(paramObject.toString())
                Log.e("response", response.body().toString())
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }

    fun borrarUsuario(user: User) {

        val paramObject = JSONObject()
        paramObject.put(
            "query",
            "mutation { deleteUser(id:${user.id}){id,name,last_name,email,password,phone,payment_method,address}}"
        )

        GlobalScope.launch {
            try {
                val response = retrofit.sendRequest(paramObject.toString())
                Log.e("response", response.body().toString())
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }
}