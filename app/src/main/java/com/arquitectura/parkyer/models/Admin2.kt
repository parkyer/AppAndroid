package com.arquitectura.parkyer.models

import com.google.gson.annotations.SerializedName

data class Mensaje(
    @SerializedName("idmensajes") var idMensajes: Int? = 0,
    @SerializedName("id_usuario") var idUsuario: Int? = 0,
    @SerializedName("mensaje") var mensaje: String? = "",
    @SerializedName("tipo") var tipo: String? = ""
)

data class Respuesta(
    @SerializedName("mensaje") val mensaje: String? = ""
)