package com.example.fruitapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Fruit(
    val type: String,
    val price: String,
    val weight: String
) : Parcelable
