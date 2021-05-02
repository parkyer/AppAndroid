package com.arquitectura.parkyer.microservicios

import com.arquitectura.parkyer.models.User
import com.arquitectura.parkyer.service.functions.FunctionsPerfil

class MicroServicioPerfil {

    private val functionsPerfil = FunctionsPerfil()
    private var user = User()

    fun obtenerUsuario(id: Int) {
        functionsPerfil.obtenerUsuario(id)
    }

    fun editarUsuario() {
        functionsPerfil.editarUsuario(user)
    }

    fun cambiarContrasenia() {
        functionsPerfil.cambiarContrasenia(user)
    }

    fun modificarUser(name: String, address: String, email: String, lastName: String, phone: Int, id: Int, password: String) {
        this.user.name = name
        this.user.address = address
        this.user.email = email
        this.user.lastName = lastName
        this.user.phone = phone
        this.user.id = id
        this.user.password = password
    }
}