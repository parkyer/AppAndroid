package com.arquitectura.parkyer.microservicios

import com.arquitectura.parkyer.models.Mensaje
import com.arquitectura.parkyer.service.functions.FunctionsContacto

class MicroServicioContacto {

    private val functionsContacto = FunctionsContacto()
    private var parking = Parking(
        1,
        1,
        "null",
        "4.740596106774402",
        "-74.03132287148797", 
        "calle 141bis #16a - 39",
        "carro"
    )

    fun getParkings() {
        functionsContacto.getParkings()
    }

    fun getParkingsUsedBy(id_client: String) {
        functionsContacto.getParkingsUsedBy(id_client)
    }

    fun getAvailableParkings() {
        functionsContacto.getAvailableParkings()
    }

    fun newSuscription(id: Int, id_client: String) {
        functionsContacto.newSuscription(id, id_client)
    }

    fun deleteSuscription(id: Int) {
        functionsContacto.deleteSuscription(id)
    }
}