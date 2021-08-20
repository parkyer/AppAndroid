package com.arquitectura.parkyer.microservicios

import com.arquitectura.parkyer.service.functions.FunctionsPerfil
import io.reactivex.Single

class MicroServicioPerfil() {

    private val functionsPerfil = FunctionsPerfil()

    fun obtenerUsuario(id: Int): Single<String> {
        return functionsPerfil.obtenerUsuario(id)
    }
}