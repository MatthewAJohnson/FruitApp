package com.example.fruitapp.remote.mappers

import com.example.fruitapp.models.Fruit
import com.example.fruitapp.models.FruitList
import com.example.fruitapp.models.FruitResult

fun FruitList.fruit(): List<Fruit> = this.fruit?.map {
    convertToFruit(it)
}.orEmpty()

fun convertToFruit(fruit: FruitResult?): Fruit {
    return Fruit(
        type = fruit?.type ?: "",
        price = convertCurrency(fruit?.price),
        weight = convertWeight(fruit?.weight)
    )
}

fun convertCurrency(price: Int?): String = price?.let {
    val result = String.format("%.2f", it / 100f)
    "£$result"
} ?: "£0.00"

fun convertWeight(weight: Int?): String = weight?.let {
    val result = String.format("%.2f", it / 100f)
    "$result KG"
} ?: "0.00 KG"


