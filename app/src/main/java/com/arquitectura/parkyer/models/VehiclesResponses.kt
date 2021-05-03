package com.arquitectura.parkyer.models

import com.google.gson.annotations.SerializedName

data class Vehicle(
    @SerializedName("id") var id: Int? = 0,
    @SerializedName("id_client") var id_client: Int? = 0,
    @SerializedName("tipo") var tipo: String? = "",
    @SerializedName("tamano") var tamano: String? = "",
    @SerializedName("descripcion") var descripcion: String? = ""
)