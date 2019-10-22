package com.example.openweather.repositories

import com.example.openweather.datamodel.CityDataResponse

interface DataProvider {
    fun getCities(): List<CityDataResponse.City>
    fun addCity(city: CityDataResponse.City)
}
