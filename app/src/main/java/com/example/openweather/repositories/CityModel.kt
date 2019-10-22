package com.example.kamino.datamodel

import com.example.openweather.datamodel.CityDataResponse
import com.example.openweather.repositories.DataProvider
import com.example.openweather.repositories.WeatherApiService

class CityModel(apiService: WeatherApiService, dataProvider: DataProvider) {

    private var apiService: WeatherApiService = apiService
    private var dataProvider: DataProvider = dataProvider

    fun getCitiesFromDataProvider(): List<CityDataResponse.City> {
        return dataProvider.getCities()
    }

    fun saveCity(city: CityDataResponse.City) {
        dataProvider.addCity(city)
    }



}