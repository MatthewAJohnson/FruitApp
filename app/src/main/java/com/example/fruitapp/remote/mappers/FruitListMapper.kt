package com.example.fruitapp.remote.mappers

import com.example.fruitapp.models.Fruit
import com.example.fruitapp.models.FruitList

fun FruitList.fruit(): List<Fruit> = this.fruit?.map {
    convertToPrize(it)
}.orEmpty()

fun convertToPrize(fruit: Fruit?): Fruit {
    return Fruit(type = fruit?.type?: "")
}


