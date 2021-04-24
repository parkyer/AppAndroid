package com.arquitectura.parkyer.service

import com.arquitectura.parkyer.utils.URL_MICROSERVICIO_CONTACTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ServicesApi {

    @Headers("Content-Type: application/json")
    @POST("graphql")
    suspend fun sendRequest(@Body request: String): Response<String>
}