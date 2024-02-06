package com.example.onlineshopapp.data.api


import com.example.onlineshopapp.domain.ListOfShopItems
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("v3/97e721a7-0a66-4cae-b445-83cc0bcf9010")
    fun getShopList(): Call<ListOfShopItems>

}