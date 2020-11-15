package com.example.fruitapp.models

import com.google.gson.annotations.SerializedName

data class Fruit(
    @SerializedName("type")
    val type : String?
)
