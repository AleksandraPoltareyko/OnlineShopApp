package com.example.onlineshopapp.domain


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(
    @SerializedName("available")
    val available: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("feedback")
    val feedback: Feedback?,
    @SerializedName("id")
    val id: String,
    @SerializedName("info")
    val info: List<Info>,
    @SerializedName("ingredients")
    val ingredients: String,
    @SerializedName("price")
    val price: Price,
    @SerializedName("subtitle")
    val subtitle: String,
    @SerializedName("tags")
    val tags: List<String>,
    @SerializedName("title")
    val title: String,
    var isFavorite: Boolean = false
) : Parcelable