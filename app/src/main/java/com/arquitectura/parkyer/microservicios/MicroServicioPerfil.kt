package com.arquitectura.parkyer.microservicios

import com.arquitectura.parkyer.MainActivity
import com.arquitectura.parkyer.models.User
import com.arquitectura.parkyer.service.functions.FunctionsPerfil

class MicroServicioPerfil() {

    private val functionsPerfil = FunctionsPerfil()
    var user = User(
        id = 1,
        name = "Camilo",
        lastName = "Carranza",
        email = "ccarranzac@gmail.com",
        password = "password123",
        phone = 12345678,
        paymentMethod = 123456789,
        address = "calle 123"
    )

    fun obtenerUsuario(id: Int){
        user.name = functionsPerfil.obtenerUsuario(id).name
        user.lastName = functionsPerfil.obtenerUsuario(id).lastName
        user.id = functionsPerfil.obtenerUsuario(id).id
        user.address = functionsPerfil.obtenerUsuario(id).address
        user.paymentMethod = functionsPerfil.obtenerUsuario(id).paymentMethod
        user.email = functionsPerfil.obtenerUsuario(id).email
        user.phone = functionsPerfil.obtenerUsuario(id).phone
        user.password = functionsPerfil.obtenerUsuario(id).password
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