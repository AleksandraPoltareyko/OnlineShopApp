package com.example.onlineshopapp.presentation

import com.example.onlineshopapp.domain.Item
import com.example.onlineshopapp.domain.User

interface Postman {

    fun fragmentMail(user: User?, shopList: List<Item>?)

}