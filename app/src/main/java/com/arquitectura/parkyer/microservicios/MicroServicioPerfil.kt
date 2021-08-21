package com.arquitectura.parkyer.microservicios

import com.arquitectura.parkyer.models.User
import com.arquitectura.parkyer.service.functions.FunctionsPerfil
import io.reactivex.Single

class MicroServicioPerfil() {

    private val functionsPerfil = FunctionsPerfil()

    fun obtenerUsuario(id: Int): Single<String> {
        return functionsPerfil.obtenerUsuario(id)
    }

    fun editarUsuario(user: User): Single<String> {
        return functionsPerfil.editarUsuario(user)
    }

    fun cambiarContrasenia(user: User): Single<String> {
        return functionsPerfil.cambiarContrasenia(user)
    }

    fun agregarMetodoDePago(user: User): Single<String> {
        return functionsPerfil.agregarMetodoDePago(user)
    }

    fun borrarUsuario(user: User): Single<String> {
        return functionsPerfil.borrarUsuario(user)
    }
}