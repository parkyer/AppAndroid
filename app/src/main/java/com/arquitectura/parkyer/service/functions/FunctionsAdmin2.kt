package com.arquitectura.parkyer.service.functions

import android.util.Log
import com.a.graphqlwithretrofit.ServiceBuilder
import com.arquitectura.parkyer.models.Parking
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Single
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject

class FunctionsAdmin2 {

    private val retrofit = ServiceBuilder.serviceBuilder
    private val arrayTutorialType = object : TypeToken<List<Parking>>() {}.type

    private var Parkings: List<Parking>? = emptyList()
    private var UsedBy: List<Parking>? = emptyList()
    private var Availables: List<Parking>? = emptyList()
    private var suscription: Parking? = Parking()
    private var unsuscription: Parking? = Parking()

    fun getParkings() {

        val paramObject = JSONObject()
        paramObject.put(
                "query",
                "query {getParkings{id,id_owner,id_client,latitude,longitude,location,type}}"
        )

        /*GlobalScope.launch {
            try {
                val response = retrofit.sendRequest(paramObject.toString())
                val data = JSONObject(response.body().toString())
                Parkings = Gson().fromJson(
                    JSONObject(data.get("data").toString()).get("getParkings").toString(),
                    arrayTutorialType
                )
                Log.e("response", Parkings.toString())
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }*/
    }

    fun getParkingsUsedBy(id_client: Int? = 0) : Single<String> {

        val paramObject = JSONObject()
        paramObject.put(
                "query",
                "query {getParkingsUsedBy(id: ${id_client}){id,id_owner,id_client,latitude,longitude,location,type}}"
        )

        return retrofit.sendRequest(paramObject.toString())

        /* GlobalScope.launch {
            try {
                val response = retrofit.sendRequest(paramObject.toString())
                val data = JSONObject(response.body().toString())
                Log.e("response", UsedBy.toString())
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }*/
    }

    fun getAvailableParkings(): Single<String> {

        val paramObject = JSONObject()
        paramObject.put(
                "query",
                "query {getAvailableParkings{id,id_owner,id_client,latitude,longitude,location,type}}"
        )

        return retrofit.sendRequest(paramObject.toString())

        /*GlobalScope.launch {
        try {
            val response = retrofit.sendRequest(paramObject.toString())
            val data = JSONObject(response.body().toString())
            Availables = Gson().fromJson(
                JSONObject(data.get("data").toString()).get("getAvailableParkings").toString(),
                arrayTutorialType
            )
            Log.e("response", Availables.toString())
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }*/
    }

    fun newSuscription(id: Int? = 0, id_client: String): Single<String> {

        val paramObject = JSONObject()
        paramObject.put(
                "query",
                "mutation {newSuscription(id:${id},client:{id_client:\"${id_client}\"}){id,id_owner,id_client,latitude,longitude,location,type}}"
        )

        return retrofit.sendRequest(paramObject.toString())
        /*GlobalScope.launch {
        try {
            val response = retrofit.sendRequest(paramObject.toString())
            val data = JSONObject(response.body().toString())
            suscription = Gson().fromJson(
                JSONObject(
                    data.get("data").toString()
                ).getString("newSuscription").toString(), Parking::class.java
            )
            suscription?.let {
                Log.e("response", it.id.toString())
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }*/
    }

    fun deleteSuscription(id: Int? = 0): Single<String> {

        val paramObject = JSONObject()
        paramObject.put(
                "query",
                "mutation {deleteSuscription(id:${id}){id,id_owner,id_client,latitude,longitude,location,type}}"
        )

        return retrofit.sendRequest(paramObject.toString())

        /*GlobalScope.launch {
        try {
            val response = retrofit.sendRequest(paramObject.toString())
            val data = JSONObject(response.body().toString())
            unsuscription = Gson().fromJson(
                JSONObject(
                    data.get("data").toString()
                ).getString("deleteSuscription").toString(), Parking::class.java
            )
            unsuscription?.let {
                Log.e("response", it.id.toString())
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }*/
    }
}
