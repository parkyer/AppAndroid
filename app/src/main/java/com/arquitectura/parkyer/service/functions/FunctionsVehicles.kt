package com.arquitectura.parkyer.service.functions

import android.util.Log
import com.a.graphqlwithretrofit.ServiceBuilder
import com.arquitectura.parkyer.models.Vehicle
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject

class FunctionsVehicles {

    private val retrofit = ServiceBuilder.serviceBuilder
    private val arrayTutorialType = object : TypeToken<List<Vehicle>>() {}.type

    private var Vehicles: List<Vehicle>? = emptyList()
    private var vehicle: Vehicle? = Vehicle()
    private var newvehicle: Vehicle? = Vehicle()
    private var updatedvehicle: Vehicle? = Vehicle()

    fun getAll() {

        val paramObject = JSONObject()
        paramObject.put(
            "query",
            "query {getAllVehicles{id,id_client,tipo,tamano,descripcion}}")

        /*GlobalScope.launch {
            try {
                val response = retrofit.sendRequest(paramObject.toString())
                val data = JSONObject(response.body().toString())
                Vehicles = Gson().fromJson(
                    JSONObject(data.get("data").toString()).get("getAllVehicles").toString(),
                    arrayTutorialType
                )
                Log.e("response", Vehicles.toString())
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }*/
    }

    fun getByID(id: Int) {

        val paramObject = JSONObject()
        paramObject.put(
            "query", 
            "query {getVehicle(id: ${id}){id,id_client,tipo,tamano,descripcion}}")

        /*GlobalScope.launch {
            try {
                val response = retrofit.sendRequest(paramObject.toString())
                val data = JSONObject(response.body().toString())
                vehicle = Gson().fromJson(
                    JSONObject(data.get("data").toString()).get("getVehicle").toString(),
                    Vehicle::class.java
                )
                Log.e("response", vehicle.toString())
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }*/
    }

    fun new(id_client: Int, tipo: String, tamano: String, descripcion: String) {

        val paramObject = JSONObject()
        paramObject.put(
            "query", 
            "mutation {createVehicle(vehicle: {id_client: ${id_client},tipo: \"${tipo}\",tamano: \"${tamano}\",descripcion: \"${descripcion}\"}){id,id_client,tipo,tamano,descripcion}}")

        /*GlobalScope.launch {
            try {
                val response = retrofit.sendRequest(paramObject.toString())
                val data = JSONObject(response.body().toString())
                newvehicle = Gson().fromJson(
                    JSONObject(data.get("data").toString()).get("createVehicle").toString(),
                    Vehicle::class.java
                )
                Log.e("response", newvehicle.toString())
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }*/
    }

    fun update(id: Int, tipo: String, tamano: String, descripcion: String) {

        val paramObject = JSONObject()
        paramObject.put(
            "query",
            "mutation {updateVehicle(id: ${id},vehicle: {tipo: \"${tipo}\",tamano: \"${tamano}\",descripcion: \"${descripcion}\"}){id,id_client,tipo,tamano,descripcion}}")

        /*GlobalScope.launch {
            try {
                val response = retrofit.sendRequest(paramObject.toString())
                val data = JSONObject(response.body().toString())
                updatedvehicle = Gson().fromJson(
                    JSONObject(data.get("data").toString()).get("updateVehicle").toString(),
                    Vehicle::class.java
                )
                Log.e("response", updatedvehicle.toString())
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }*/
    }

    fun delete(id: Int) {

        val paramObject = JSONObject()
        paramObject.put(
            "query", 
            "mutation {deleteVehicle(id: ${id})}"
        )

        /*GlobalScope.launch {
            try {
                val response = retrofit.sendRequest(paramObject.toString())
                Log.e("response", response.toString())
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }*/
    }
    
}