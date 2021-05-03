package com.arquitectura.parkyer.microservicios

import com.arquitectura.parkyer.models.Vehicle
import com.arquitectura.parkyer.service.functions.FunctionsVehicles

class MicroServicioVehicles {

    private val function_vehicles = FunctionsVehicles()
    private var vehicle = Vehicle(
        1,
        2,
        "carro",
        "grande",
        "mazda rojo"
    )

    fun modificarVehiculo(id: Int, id_client: Int, tipo: String, tamano: String, descripcion: String) {
        this.vehicle.id = id
        this.vehicle.id_client = id_client
        this.vehicle.tipo = tipo
        this.vehicle.tamano = tamano
        this.vehicle.descripcion = descripcion
    }

    fun getAllVehicles() {
        function_vehicles.getAll()
    }

    fun getVehicle(id: Int) {
        function_vehicles.getByID(id)
    }

    fun createVehicle(id_client: Int, tipo: String, tamano: String, descripcion: String) {
        function_vehicles.new(id_client, tipo, tamano, descripcion)
    }

    fun updateVehicle(id: Int, tipo: String, tamano: String, descripcion: String) {
        function_vehicles.update(id, tipo, tamano, descripcion)
    }

    fun deleteVehicle(id: Int) {
        function_vehicles.delete(id)
    }
}