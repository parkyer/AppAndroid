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

    fun modificarParking(id: Int, id_owner: Int, id_client: String, latitude: String, longitude: String, location: String, type: String) {
        this.parking.id = id
        this.parking.id_owner = id_owner
        this.parking.id_client = id_client
        this.parking.latitude = latitude
        this.parking.longitude = longitude
        this.parking.location = location
        this.parking.type = type
    }

    fun getParkings() {
        functionsadmin2.getParkings()
    }

    fun getParkingsUsedBy(id_client) {
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