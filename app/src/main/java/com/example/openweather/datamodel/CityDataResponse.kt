package com.example.openweather.datamodel

import com.google.gson.annotations.SerializedName

open class CityDataResponse {


    data class WeatherData(
        @SerializedName("main")
        val mainData: MainData

    )

    data class MainData(
        @SerializedName("humidity")
        val humidity: String,
        @SerializedName("temp")
        val temperature: String

    )


}

