package com.arquitectura.parkyer.microservicios

import com.arquitectura.parkyer.MainActivity
import com.arquitectura.parkyer.models.User
import com.arquitectura.parkyer.service.functions.FunctionsPerfil

class MicroServicioPerfil(mainActivity: MainActivity) {

    private val functionsPerfil = FunctionsPerfil()
    private var user = User(
        id = 1,
        name = "Camilo",
        lastName = "Carranza",
        email = "ccarranzac@gmail.com",
        password = "password123",
        phone = 12345678,
        paymentMethod = 123456789,
        address = "calle 123"
    )

    fun obtenerUsuario(id: Int) {
        functionsPerfil.obtenerUsuario(id)
    }

    fun editarUsuario() {
        functionsPerfil.editarUsuario(user)
    }

    fun cambiarContrasenia() {
        functionsPerfil.cambiarContrasenia(user)
    }

    fun agregarMetodoDePago() {
        functionsPerfil.agregarMetodoDePago(user)
    }

    fun borrarUsuario() {
        functionsPerfil.borrarUsuario(user)
    }

    fun modificarUser(
        name: String,
        address: String,
        email: String,
        lastName: String,
        phone: Int,
        id: Int,
        password: String,
        paymentMethod: Int
    ) {
        this.user.name = name
        this.user.address = address
        this.user.email = email
        this.user.lastName = lastName
        this.user.phone = phone
        this.user.id = id
        this.user.password = password
        this.user.paymentMethod = paymentMethod
    }
}