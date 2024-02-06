package com.example.onlineshopapp.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {

    fun getItem(id: String) : Item

    fun getShopList(): LiveData<ListOfShopItems>

}