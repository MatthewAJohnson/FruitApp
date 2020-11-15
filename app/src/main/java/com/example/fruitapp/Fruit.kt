package com.example.fruitapp

import com.google.gson.annotations.SerializedName

data class Fruit(
    @SerializedName("type")
    val type : String?
)
