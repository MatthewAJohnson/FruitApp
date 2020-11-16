package com.example.fruitapp.models

import com.google.gson.annotations.SerializedName

data class FruitResult(
    @SerializedName("type")
    val type : String?,
    @SerializedName("price")
    val price: Int?,
    @SerializedName("weight")
    val weight: Int?
)
