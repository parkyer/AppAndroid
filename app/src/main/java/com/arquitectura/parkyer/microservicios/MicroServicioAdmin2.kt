package com.arquitectura.parkyer.microservicios

import com.arquitectura.parkyer.models.Parking
import com.arquitectura.parkyer.service.functions.FunctionsAdmin2

class MicroServicioAdmin2 {

    private val functionsadmin2 = FunctionsAdmin2()
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
        functionsadmin2.getParkings()
    }

    fun getParkingsUsedBy(id_client: String) {
        functionsadmin2.getParkingsUsedBy(id_client)
    }

    fun getAvailableParkings() {
        functionsadmin2.getAvailableParkings()
    }

    fun newSuscription(id: Int, id_client: String) {
        functionsadmin2.newSuscription(id, id_client)
    }

    fun deleteSuscription(id: Int) {
        functionsadmin2.deleteSuscription(id)
    }
}