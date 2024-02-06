package com.example.onlineshopapp.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.onlineshopapp.data.dataBase.AppDatabase
import com.example.onlineshopapp.domain.User
import com.example.onlineshopapp.domain.UserRepository
import com.example.onlineshopapp.presentation.MainActivity

class UserRepositoryImpl(application: Application) : UserRepository {

    private val userDao = AppDatabase.getInstance(application).userDao()
    private val mapper = UserMapper()

    override suspend fun addUser(user: User) {
        userDao.insertUser(mapper.mapEntityToDbModel(user))
    }

    override fun getUser(name: String, surname: String, phone: String): User?{

        val dbModel = userDao.getUser(phone)
        if (dbModel == null){
            return null
        }
        return mapper.mapDbModelToEntity(dbModel)
    }

    override fun getUsers(): LiveData<List<User>> = userDao.getUsers().map {
        mapper.mapListDbModelToListEntity(it)
    }

    override suspend fun changeFavorites() {
        if (MainActivity.user != null) {
            userDao.updateUser(mapper.mapEntityToDbModel(MainActivity.user!!))
        }
    }
}