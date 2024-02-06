package com.example.onlineshopapp.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Info(
    @SerializedName("title")
    val title: String,
    @SerializedName("value")
    val value: String
) : Parcelable