package com.example.onlineshopapp.domain


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Price(
    @SerializedName("discount")
    val discount: Int,
    @SerializedName("price")
    val price: String,
    @SerializedName("priceWithDiscount")
    val priceWithDiscount: String,
    @SerializedName("unit")
    val unit: String
) : Parcelable