package com.example.kamino.datamodel

import com.google.gson.annotations.SerializedName

open class WeatherModel {
    data class KaminoPlanet(
        @SerializedName("name")
        val name: String,
        @SerializedName("rotation_period")
        val rotationPeriod: Int


    )
}