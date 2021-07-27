package com.arquitectura.parkyer.models

import com.google.gson.annotations.SerializedName

data class UserLogin(
    @SerializedName("id") var id: Int? = 0,
    @SerializedName("name") var name: String? = "",
    @SerializedName("last_name") var last_name: String? = "",
    @SerializedName("email") var email: String? = "",
    @SerializedName("password") var password: String? = "",
    @SerializedName("phone") var phone: Int? = 0,
    @SerializedName("payment_method") var payment_method: Int? = 0,
    @SerializedName("address") var address: String? = ""
)

data class Login(
    @SerializedName("access") var name: String? = "",
    @SerializedName("id") var id: Int? = 0
)
