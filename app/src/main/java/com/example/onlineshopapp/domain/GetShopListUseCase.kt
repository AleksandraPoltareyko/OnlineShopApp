package com.example.onlineshopapp.domain

import androidx.lifecycle.LiveData

class GetShopListUseCase(private val repository: ShopListRepository) {
    fun getShopList():LiveData<ListOfShopItems>{
        return repository.getShopList()
    }
}