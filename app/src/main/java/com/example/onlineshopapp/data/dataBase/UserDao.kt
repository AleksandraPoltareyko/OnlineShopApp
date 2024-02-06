package com.example.onlineshopapp.data.dataBase

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.onlineshopapp.data.UserDBModel

@Dao
interface UserDao {

    @Query("SELECT * FROM user_list WHERE phone == :phone LIMIT 1")
    fun getUser(phone: String):UserDBModel

    @Query("SELECT * FROM user_list")
    fun getUsers():LiveData<List<UserDBModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userDBModel: UserDBModel)

    @Update
    suspend fun updateUser(userDBModel: UserDBModel)

}