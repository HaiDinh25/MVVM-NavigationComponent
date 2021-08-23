package com.haidv.userlisttest.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIConfig {

    private const val BASE_URL = "https://api.github.com"
    private val builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
    private val retrofit = builder.build()
    val apiService: APIService = retrofit.create(APIService::class.java)

}