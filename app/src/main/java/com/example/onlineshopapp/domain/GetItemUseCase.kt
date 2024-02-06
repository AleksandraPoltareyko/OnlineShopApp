package com.example.onlineshopapp.domain

class GetItemUseCase(private val repository: ShopListRepository) {
    fun getItem(id: String): Item{
        return repository.getItem(id)
    }
}