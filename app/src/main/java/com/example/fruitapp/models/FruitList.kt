package com.example.fruitapp.models

import com.google.gson.annotations.SerializedName

data class FruitList (
    @SerializedName("fruit")
    val fruit: List<FruitResult?>?
)
