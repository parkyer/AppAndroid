package com.arquitectura.parkyer.service.functions

import com.a.graphqlwithretrofit.ServiceBuilder
import com.arquitectura.parkyer.models.Parking
import com.google.gson.reflect.TypeToken
import io.reactivex.Single
import org.json.JSONObject

class FunctionsAdmin1 {

    private val retrofit = ServiceBuilder.serviceBuilder
    private val arrayTutorialType = object : TypeToken<List<Parking>>() {}.type

    private var Parkings: List<Parking>? = emptyList()
    private var Parking: Parking? = Parking()
    private var Created: Parking? = Parking()
    private var update_status: String? = null
    private var delete_status: Int? = 0

    fun getParkingsCreated(): Single<String> {

        val paramObject = JSONObject()
        paramObject.put(
            "query",
            "query {getParkingsCreated{id,id_owner,id_client,latitude,longitude,location,type}}"
        )

        return retrofit.sendRequest(paramObject.toString())

        /*
            Parkings = Gson().fromJson(
                JSONObject(
                    data.get("data").toString()
                ).getString("getParkingsCreated").toString(), arrayTutorialType
            )*/
    }

    fun getParkingById(id: Int?): Single<String> {

        val paramObject = JSONObject()
        paramObject.put(
            "query",
            "query {getParkingById(id: ${id}){id,id_owner,id_client,latitude,longitude,location,type}}"
        )

        return retrofit.sendRequest(paramObject.toString())

        /*
            Parking = Gson().fromJson(
                JSONObject(
                    data.get("data").toString()
                ).getString("getParkingById").toString(), Parking::class.java
            )*/


    }

    fun updateParkingById(parking: Parking) : Single<String> {

        val paramObject = JSONObject()
        paramObject.put(
            "query",
            "mutation {updateParkingById(id: ${parking.id}, parking: {id_owner: ${parking.id_owner},id_client: ${parking.id_client},latitude: ${parking.latitude},longitude: ${parking.longitude},location: ${parking.location},type: ${parking.type}}) }"
        )

        return retrofit.sendRequest(paramObject.toString())

        /* update_status = Gson().fromJson(
                JSONObject(data.get("data").toString()).get("updateParkingById").toString(),
                String?
                )*/
    }

    fun deleteParkingById(id: Int? = 0): Single<String> {

        val paramObject = JSONObject()
        paramObject.put(
            "query",
            "mutation {deleteParkingById(id: ${id})}"
        )

        return retrofit.sendRequest(paramObject.toString())

        /*delete_status = Gson().fromJson(
            JSONObject(data.get("data").toString()).get("deleteParkingById").toString(),
            Int?
            )*/

    }

    fun createParking(parking: Parking): Single<String> {

        val paramObject = JSONObject()
        paramObject.put(
            "query",
            "mutation { createParking(parking: {id: ${parking.id}, id_owner: ${parking.id_owner}, id_client: ${parking.id_client}, latitude: ${parking.latitude}, longitude: ${parking.longitude}, location: ${parking.location}, type: ${parking.type}  })" +
                    "{id, id_owner, id_client, latitude, longitude, location, type} }"
        )

        return retrofit.sendRequest(paramObject.toString())


        /*
            Created = Gson().fromJson(
                JSONObject(
                    data.get("data").toString()
                ).getString("createParking").toString(), Parking::class.java
            )
         */
    }

}