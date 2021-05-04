package com.arquitectura.parkyer.microservicios

import com.arquitectura.parkyer.models.Register
import com.arquitectura.parkyer.service.functions.FunctionsRegister

class MicroServicioRegister {

    private val function_register = FunctionsRegister()
    private var register = Register(
        "1",
        2,
        4,
        "tipo 1",
        "mayo 3 de 2021"
    )

    fun modificarRegistro(Id: String, User: Int, ParkingId: Int, Type: String, Date: String) {
        this.register.Id = Id
        this.register.User = User
        this.register.ParkingId = ParkingId
        this.register.Type = Type
        this.register.Date = Date
    }

    fun getRegister(id: String) {
        function_register.getByID(id)
    }

    fun getRegisters() {
        function_register.getAll()
    }

    fun getRegisterUser(user: Int) {
        function_register.getUser(user)
    }

    fun getRegisterParking(parking: Int) {
        function_register.getParking(parking)
    }

    fun createRegister(user: Int, parking: Int, type: String, date: String) {
        function_register.create(user, parking, type, date)
    }

    fun deleteRegister(id: String) {
        function_register.delete(id)
    }

}