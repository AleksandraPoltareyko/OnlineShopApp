package com.example.onlineshopapp.data.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    //https://run.mocky.io/
    // v3/97e721a7-0a66-4cae-b445-83cc0bcf9010

    private const val BASE_URL = "https://run.mocky.io/"
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val apiSrevice = retrofit.create(ApiService::class.java)

}