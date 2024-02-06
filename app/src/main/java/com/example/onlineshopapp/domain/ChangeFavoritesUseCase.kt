package com.example.onlineshopapp.domain

import com.example.onlineshopapp.presentation.MainActivity

class ChangeFavoritesUseCase(private val repository: UserRepository) {

    suspend fun changeFavorites(itemId:String, isFavorite:Boolean){
        var favorites = MainActivity.user?.favorites
        if (favorites != null) {
            favorites = if (isFavorite){
                "$favorites;$itemId"
            }else{
                favorites.replace("$itemId;", "")
            }
            MainActivity.user?.favorites = favorites
        }
        repository.changeFavorites()
    }
}