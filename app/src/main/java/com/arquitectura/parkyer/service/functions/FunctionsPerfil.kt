package com.arquitectura.parkyer.service.functions

import com.a.graphqlwithretrofit.ServiceBuilder
import com.arquitectura.parkyer.models.User
import io.reactivex.Single
import org.json.JSONObject

class FunctionsPerfil {

    private val retrofit = ServiceBuilder.serviceBuilder

    fun obtenerUsuario(id: Int): Single<String> {

        val paramObject = JSONObject()
        paramObject.put(
            "query",
            "query{ getUser(id: ${id}) {id, name, last_name, email,address,password,payment_method,phone}}"
        )

        return retrofit.sendRequest(paramObject.toString())
    }

    fun editarUsuario(user: User): Single<String> {

        val paramObject = JSONObject()
        paramObject.put(
            "query",
            "mutation { updateUser(id: ${user.id}, user: {name: \"${user.name}\", last_name: \"${user.lastName}\", email: \"${user.email}\", phone: ${user.phone}, address: \"${user.address}\"}) {id,name,last_name,email,password,phone,payment_method,address}}"
        )

        return retrofit.sendRequest(paramObject.toString())
    }

    fun cambiarContrasenia(user: User): Single<String> {

        val paramObject = JSONObject()
        paramObject.put(
            "query",
            "mutation { changePassword(id: ${user.id}, password: {password: \"${user.password}\"}){id,name,last_name,email,password,phone,payment_method,address}}"
        )

        return retrofit.sendRequest(paramObject.toString())
    }

    fun agregarMetodoDePago(user: User): Single<String> {

        val paramObject = JSONObject()
        paramObject.put(
            "query",
            "mutation { addPaymentMethod(id: ${user.id}, payment: {payment_method: \"${user.paymentMethod}\"}){id,name,last_name,email,password,phone,payment_method,address}}"
        )

        return retrofit.sendRequest(paramObject.toString())
    }

    fun borrarUsuario(user: User): Single<String> {

        val paramObject = JSONObject()
        paramObject.put(
            "query",
            "mutation { deleteUser(id:${user.id}){id,name,last_name,email,password,phone,payment_method,address}}"
        )

        return retrofit.sendRequest(paramObject.toString())
    }
}