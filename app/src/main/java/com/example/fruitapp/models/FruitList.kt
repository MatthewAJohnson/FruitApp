package com.example.fruitapp.models

import com.example.fruitapp.models.Fruit
import com.google.gson.annotations.SerializedName

data class FruitList (
    @SerializedName("fruit")
    val fruit: List<Fruit?>?
)
