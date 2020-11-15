package com.example.fruitapp

import com.google.gson.annotations.SerializedName

data class FruitList (
    @SerializedName("fruit")
    val fruit: List<Fruit?>?
)
