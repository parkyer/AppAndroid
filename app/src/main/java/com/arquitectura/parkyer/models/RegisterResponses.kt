package com.arquitectura.parkyer.models

import com.google.gson.annotations.SerializedName

data class Register(
    @SerializedName("Id") var Id: String? = "",
    @SerializedName("User") var User: Int? = 0,
    @SerializedName("ParkingId") var ParkingId: Int? = 0,
    @SerializedName("Type") var Type: String? = "",
    @SerializedName("Date") var Date: String? = ""
)



