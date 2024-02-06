package com.example.onlineshopapp.domain

class AddUserUseCase(private val repository: UserRepository) {

    suspend fun addUser(user: User) {
        return repository.addUser(user)
    }

}