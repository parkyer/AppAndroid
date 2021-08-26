package com.arquitectura.parkyer.microservicios

import com.arquitectura.parkyer.models.Parking
import com.arquitectura.parkyer.service.functions.FunctionsAdmin1
import io.reactivex.Single

class MicroServicioAdmin1 {

    var parkingOwnerArray = ArrayList<Parking>()

    private val functions_admin1 = FunctionsAdmin1()
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

    fun getParkinsCreated(): Single<String> {
        return functions_admin1.getParkingsCreated()
    }

    fun getParkingById(id: Int): Single<String> {
        return functions_admin1.getParkingById(id)
    }

    fun updateParkingById(parking: Parking): Single<String> {
        return functions_admin1.updateParkingById(parking)
    }

    fun deleteParkingById(id: Int?): Single<String> {
        return functions_admin1.deleteParkingById(id)
    }

    fun createParking(parking: Parking): Single<String> {
        return functions_admin1.createParking(parking)
    }

}