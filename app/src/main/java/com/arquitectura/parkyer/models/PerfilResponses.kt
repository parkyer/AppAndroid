package com.arquitectura.parkyer.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id") var id: Int? = 0,
    @SerializedName("name") var name: String? = "",
    @SerializedName("last_name") var lastName: String? = "",
    @SerializedName("email") var email: String? = "",
    @SerializedName("password") var password: String? = "",
    @SerializedName("phone") var phone: Int? = 0,
    @SerializedName("payment_method") var paymentMethod: Int? = 0,
    @SerializedName("address") var address: String? = ""
)

data class UserInput(
    @SerializedName("name") var name: String? = "",
    @SerializedName("last_name") var lastName: String? = "",
    @SerializedName("email") var email: String? = "",
    @SerializedName("password") var password: String? = "",
    @SerializedName("phone") var phone: Int? = 0,
    @SerializedName("payment_method") var paymentMethod: Int? = 0,
    @SerializedName("address") var address: String? = ""
)

data class EditUser(
    @SerializedName("name") var name: String? = "",
    @SerializedName("last_name") var lastName: String? = "",
    @SerializedName("email") var email: String? = "",
    @SerializedName("phone") var phone: Int? = 0,
    @SerializedName("address") var address: String? = ""
)

data class PasswordInput(
    @SerializedName("password") var password: String? = ""
)

data class PaymentInput(
    @SerializedName("payment_method") var paymentMethod: String? = ""
)