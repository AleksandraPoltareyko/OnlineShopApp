package com.example.onlineshopapp.domain

import androidx.lifecycle.LiveData

class GetUsersUseCase(private val repository: UserRepository) {

     fun getUsers() : LiveData<List<User>>{
         val l = repository.getUsers()
        return l
    }

}