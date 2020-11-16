package com.example.fruitapp.remote.mappers

import com.example.fruitapp.models.FruitList
import com.example.fruitapp.models.FruitResult
import org.hamcrest.core.IsEqual.equalTo
import org.junit.Assert.assertThat
import org.junit.Test

class FruitListMapperShould {

    @Test
    fun `return correctly formatted values`() {
        val fruitList = FruitList(listOf(FruitResult("apple", 500, 1000)))
        val fruit = fruitList.fruit()
        assertThat(fruit[0].type, equalTo("apple"))
        assertThat(fruit[0].price, equalTo("£5.00"))
        assertThat(fruit[0].weight, equalTo("10.00 KG"))
    }

    @Test
    fun `return default values when the fruitlist is empty`() {
        val fruitList = FruitList(listOf(FruitResult(null, null, null)))
        val fruit = fruitList.fruit()
        assertThat(fruit[0].type, equalTo(""))
        assertThat(fruit[0].price, equalTo("£0.00"))
        assertThat(fruit[0].weight, equalTo("0.00 KG"))
    }

}