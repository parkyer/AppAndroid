package com.arquitectura.parkyer.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Parking(
    @SerializedName("id") var id: Int? = 0,
    @SerializedName("id_owner") var id_owner: Int? = 0,
    @SerializedName("id_client") var id_client: String? = "",
    @SerializedName("latitude") var latitude: String? = "",
    @SerializedName("longitude") var longitude: String? = "",
    @SerializedName("location") var location: String? = "",
    @SerializedName("type") var type: String? = ""
) : Parcelable
