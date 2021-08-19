package com.arquitectura.parkyer.service.functions

import android.util.Log
import com.a.graphqlwithretrofit.ServiceBuilder
import com.arquitectura.parkyer.models.Mensaje
import com.arquitectura.parkyer.models.Respuesta
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject

class FunctionsContacto {

    private val retrofit = ServiceBuilder.serviceBuilder
    private val arrayTutorialType = object : TypeToken<List<Mensaje>>() {}.type

    private var listaMensaje: List<Mensaje>? = emptyList()
    private var respuesta: Respuesta? = Respuesta()

    fun obtenerMensajes() {

        val paramObject = JSONObject()
        paramObject.put("query", "query {getInicio{idmensaje,id_usuario,mensaje,tipo}}")

        /*GlobalScope.launch {
            try {
                val response = retrofit.sendRequest(paramObject.toString())
                val data = JSONObject(response.body().toString())
                listaMensaje = Gson().fromJson(
                    JSONObject(data.get("data").toString()).get("getInicio").toString(),
                    arrayTutorialType
                )
                Log.e("response", listaMensaje.toString())
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }*/
    }

    fun enviarMensaje(mensaje: Mensaje) {

        val paramObject = JSONObject()
        paramObject.put(
            "query",
            "mutation { crearMensaje(mensaje: { mensaje: \"${mensaje.mensaje}\", idmensaje: ${mensaje.idMensajes}, id_usuario: ${mensaje.idUsuario}, tipo: \"${mensaje.tipo}\" }) { mensaje } }"
        )
        /*GlobalScope.launch {
            try {
                val response = retrofit.sendRequest(paramObject.toString())
                val data = JSONObject(response.body().toString())
                Log.e(
                    "response",
                    JSONObject(data.get("data").toString()).getString("crearMensaje").toString()
                )
                respuesta = Gson().fromJson(
                    JSONObject(
                        data.get("data").toString()
                    ).getString("crearMensaje").toString(), Respuesta::class.java
                )
                respuesta?.let {
                    Log.e("response", it.mensaje.toString())
                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }*/
    }
}