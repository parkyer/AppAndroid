package com.arquitectura.parkyer.service.functions

import android.util.Log
import com.a.graphqlwithretrofit.ServiceBuilder
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
                Log.e("response", response.body().toString() )
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }
}