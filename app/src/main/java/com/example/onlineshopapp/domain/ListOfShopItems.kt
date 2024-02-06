package com.example.onlineshopapp.domain


import com.google.gson.annotations.SerializedName

data class ListOfShopItems(
    @SerializedName("items")
    var items: MutableList<Item>
)