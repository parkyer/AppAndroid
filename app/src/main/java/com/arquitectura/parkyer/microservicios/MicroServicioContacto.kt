package com.arquitectura.parkyer.microservicios

import com.arquitectura.parkyer.models.Mensaje
import com.arquitectura.parkyer.service.functions.FunctionsContacto

class MicroServicioContacto {

    private val functionsContacto = FunctionsContacto()
    private var mensaje = Mensaje(
        14,
        28,
        "Hola mundoooo",
        "Entrada"
    )

    fun obtenerMensajes() {
        functionsContacto.obtenerMensajes()
    }

    fun enviarMensaje() {
        functionsContacto.enviarMensaje(mensaje)
    }

    fun modificarMensaje(idMensaje: Int, idUsuario: Int, mensaje: String, tipo: String) {
        this.mensaje.idMensajes = idMensaje
        this.mensaje.idUsuario = idUsuario
        this.mensaje.mensaje = mensaje
        this.mensaje.tipo = tipo
    }
}