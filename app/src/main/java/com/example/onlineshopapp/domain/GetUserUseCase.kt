package com.example.onlineshopapp.domain

class GetUserUseCase(private val repository: UserRepository) {

    suspend fun getUser(name: String, surname: String, phone: String): User?{
        return repository.getUser(name, surname, phone)
    }

}