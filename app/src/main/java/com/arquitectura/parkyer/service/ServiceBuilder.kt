package com.a.graphqlwithretrofit

import com.arquitectura.parkyer.service.ServicesApi
import com.arquitectura.parkyer.utils.URL_MICROSERVICIO_CONTACTO
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

object ServiceBuilder {

    val serviceBuilder: ServicesApi by lazy {
        Retrofit
            .Builder()
            .baseUrl(URL_MICROSERVICIO_CONTACTO)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build().create(ServicesApi::class.java)
    }
}