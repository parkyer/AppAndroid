package com.arquitectura.parkyer.microservicios

import com.arquitectura.parkyer.models.Login
import com.arquitectura.parkyer.models.UserLogin
import com.arquitectura.parkyer.service.functions.FunctionsAuthentication

class MicroServicioAuthentication {

    private val function_authentication = FunctionsAuthentication()
    private var login = UserLogin(
        1,
        "andres",
        "ortega",
        "aortegaf@unal.edu.co",
        "hola123",
        3017538,
        1,
        "calle 1"
    )
    var loginId = Login()

    fun modificarLogin(id: Int, name: String, last_name: String, email: String, password: String, phone: Int, payment_method: Int, address: String) {
        this.login.id = id
        this.login.name = name
        this.login.last_name = last_name
        this.login.email = email
        this.login.password = password
        this.login.phone = phone
        this.login.payment_method = payment_method
        this.login.address = address
    }

    fun crearUsuario(name: String, last_name: String, email: String, password: String, phone: Int, payment_method: Int, address: String) {
        function_authentication.createUser(name, last_name, email, password, phone, payment_method, address)
    }

    fun Login(email: String, password: String): Login {
        val logInLlegada = function_authentication.login(email, password)
        loginId.id = logInLlegada.id
        loginId.name = logInLlegada.name
        return loginId
    }

}