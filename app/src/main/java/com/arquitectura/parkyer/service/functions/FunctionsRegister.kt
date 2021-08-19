package com.arquitectura.parkyer.service.functions

import android.util.Log
import com.a.graphqlwithretrofit.ServiceBuilder
import com.arquitectura.parkyer.models.Register
import com.arquitectura.parkyer.models.Vehicle
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject

class FunctionsRegister {

    private val retrofit = ServiceBuilder.serviceBuilder
    private val arrayTutorialType = object : TypeToken<List<Register>>() {}.type

    private var register: Register? = Register()
    private var Registers: List<Register>? = emptyList()
    private var RegisterUser: List<Register>? = emptyList()
    private var RegisterParking: List<Register>? = emptyList()
    private var created: Register? = Register()

    fun getByID(id: String) {

        val paramObject = JSONObject()
        paramObject.put(
            "query",
            "query {getRegister(id: \"${id}\"){Id,User,ParkingId,Type,Date}}")

        /*GlobalScope.launch {
            try {
                val response = retrofit.sendRequest(paramObject.toString())
                val data = JSONObject(response.body().toString())
                register = Gson().fromJson(
                    JSONObject(data.get("data").toString()).get("getRegister").toString(),
                    Register::class.java
                )
                Log.e("response", register.toString())
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }*/
    }

    fun getAll() {

        val paramObject = JSONObject()
        paramObject.put(
            "query", 
            "query {get_Registers{Id,User,ParkingId,Type,Date}}")

        /*GlobalScope.launch {
            try {
                val response = retrofit.sendRequest(paramObject.toString())
                val data = JSONObject(response.body().toString())
                Registers = Gson().fromJson(
                    JSONObject(data.get("data").toString()).get("get_Registers").toString(),
                    arrayTutorialType
                )
                Log.e("response", Registers.toString())
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }*/
    }

    fun getUser(user: Int) {

        val paramObject = JSONObject()
        paramObject.put(
            "query",
            "query {getRegisterUser(user: ${user}){Id,User,ParkingId,Type,Date}}")

        /*GlobalScope.launch {
            try {
                val response = retrofit.sendRequest(paramObject.toString())
                val data = JSONObject(response.body().toString())
                RegisterUser = Gson().fromJson(
                    JSONObject(data.get("data").toString()).get("getRegisterUser").toString(),
                    arrayTutorialType
                )
                Log.e("response", RegisterUser.toString())
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }*/
    }

    fun getParking(parking: Int) {

        val paramObject = JSONObject()
        paramObject.put(
            "query",
            "query {getRegisterParking(parkingId: ${parking}){Id,User,ParkingId,Type,Date}}")

        /*GlobalScope.launch {
            try {
                val response = retrofit.sendRequest(paramObject.toString())
                val data = JSONObject(response.body().toString())
                RegisterParking = Gson().fromJson(
                    JSONObject(data.get("data").toString()).get("getRegisterParking").toString(),
                    arrayTutorialType
                )
                Log.e("response", RegisterParking.toString())
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }*/
    }

    fun create(user: Int, parking: Int, type: String, date: String) {

        val paramObject = JSONObject()
        paramObject.put(
            "query",
            "mutation {createRegister(Register: {user: ${user}, parking: ${parking}, type: \"${type}\", date: \"${date}\"}){Id,User,ParkingId,Type,Date}}")

        /*GlobalScope.launch {
            try {
                val response = retrofit.sendRequest(paramObject.toString())
                val data = JSONObject(response.body().toString())
                created = Gson().fromJson(
                    JSONObject(data.get("data").toString()).get("createRegister").toString(),
                    Register::class.java
                )
                Log.e("response", created.toString())
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }*/
    }

    fun delete(id: String) {

        val paramObject = JSONObject()
        paramObject.put(
            "query",
            "mutation {deleteRegister(id: \"${id}\")}")

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