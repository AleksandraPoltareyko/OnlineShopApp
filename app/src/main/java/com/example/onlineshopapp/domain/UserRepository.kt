package com.example.onlineshopapp.domain

import androidx.lifecycle.LiveData

interface UserRepository {

    suspend fun addUser(user:User)

    fun getUser(name: String, surname: String, phone: String): User?

    fun getUsers(): LiveData<List<User>>

    suspend fun changeFavorites()


}