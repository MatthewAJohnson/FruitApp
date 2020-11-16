package com.example.fruitapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Fruit(
    @SerializedName("type")
    val type : String?
):Parcelable
