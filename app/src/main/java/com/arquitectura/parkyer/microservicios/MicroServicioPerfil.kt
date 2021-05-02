package com.arquitectura.parkyer.microservicios

import com.arquitectura.parkyer.service.functions.FunctionsPerfil

class MicroServicioPerfil {

    private val functionsPerfil = FunctionsPerfil()

    fun obtenerUsuario(id: Int) {
        functionsPerfil.obtenerUsuario(id)
    }
}