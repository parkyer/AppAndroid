package com.arquitectura.parkyer.service.functions

import android.util.Log
import com.a.graphqlwithretrofit.ServiceBuilder
import com.arquitectura.parkyer.models.User
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject

class FunctionsPerfil {

    private val retrofit = ServiceBuilder.serviceBuilder

    fun obtenerUsuario(id: Int) {

        val paramObject = JSONObject()
        paramObject.put("query", "query{ getUser(id: ${id}) {id, name, last_name, email}}")

        GlobalScope.launch {
            try {
                val response = retrofit.sendRequest(paramObject.toString())
                Log.e("response", response.body().toString())
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
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