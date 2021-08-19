package com.arquitectura.parkyer.service

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ServicesApi {

    @Headers("Content-Type: application/json")
    @POST("graphql")
    fun sendRequest(@Body request: String): Single<String>
}