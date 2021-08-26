package com.arquitectura.parkyer.service.functions

import android.util.Log
import com.a.graphqlwithretrofit.ServiceBuilder
import com.arquitectura.parkyer.models.Parking
import com.google.gson.reflect.TypeToken
import io.reactivex.Single
import org.json.JSONObject

class FunctionsAdmin1 {

    private val retrofit = ServiceBuilder.serviceBuilder

    fun getParkingsCreated(): Single<String> {

        val paramObject = JSONObject()
        paramObject.put(
            "query",
            "query {getParkingsCreated{id,id_owner,id_client,latitude,longitude,location,type}}"
        )

        return retrofit.sendRequest(paramObject.toString())
    }

    fun getParkingById(id: Int?): Single<String> {

        val paramObject = JSONObject()
        paramObject.put(
            "query",
            "query {getParkingById(id: ${id}){id,id_owner,id_client,latitude,longitude,location,type}}"
        )

        return retrofit.sendRequest(paramObject.toString())

    }

    fun updateParkingById(parking: Parking) : Single<String> {

        val paramObject = JSONObject()
        paramObject.put(
            "query",
            "mutation {updateParkingById(id: ${parking.id}, parking: {id_owner: ${parking.id_owner},id_client: ${parking.id_client},latitude: ${parking.latitude},longitude: ${parking.longitude},location: ${parking.location},type: ${parking.type}}) }"
        )

        return retrofit.sendRequest(paramObject.toString())
    }

    fun deleteParkingById(id: Int? = 0): Single<String> {

        val paramObject = JSONObject()
        paramObject.put(
            "query",
            "mutation {deleteParkingById(id: ${id})}"
        )

        return retrofit.sendRequest(paramObject.toString())

    }

    fun createParking(parking: Parking): Single<String> {

        val paramObject = JSONObject()
        paramObject.put(
            "query",
            "mutation { createParking(parking: {id: ${parking.id}, id_owner: ${parking.id_owner}, id_client: \"${parking.id_client}\", latitude: \"${parking.latitude}\", longitude: \"${parking.longitude}\", location: \"${parking.location}\", type: \"${parking.type}\"  })" +
                    "{id, id_owner, id_client, latitude, longitude, location, type} }"
        )
        Log.d("response", paramObject.toString())
        return retrofit.sendRequest(paramObject.toString())
    }

}