package com.example.onlineshopapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_list")
data class UserDBModel (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val surname: String,
    val phone: String,
    val favorites: String = ""

        )