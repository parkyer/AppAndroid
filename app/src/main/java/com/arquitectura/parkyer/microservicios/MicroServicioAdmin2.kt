package com.arquitectura.parkyer.microservicios

import com.arquitectura.parkyer.models.Parking
import com.arquitectura.parkyer.service.functions.FunctionsAdmin2
import io.reactivex.Single

class MicroServicioAdmin2 {

    private val functions_admin2 = FunctionsAdmin2()
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
        functions_admin2.getParkings()
    }

    fun getParkingsUsedBy(id_client: Int?): Single<String> {
        return functions_admin2.getParkingsUsedBy(id_client)
    }

    fun getAvailableParkings() {
        functions_admin2.getAvailableParkings()
    }

    fun newSuscription(id: Int, id_client: String) {
        functions_admin2.newSuscription(id, id_client)
    }

    fun deleteSuscription(id: Int?): Single<String> {
        return functions_admin2.deleteSuscription(id)
    }
}