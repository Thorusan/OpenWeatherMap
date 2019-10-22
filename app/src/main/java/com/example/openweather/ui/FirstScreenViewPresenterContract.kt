package com.example.openweather.ui

import com.example.openweather.datamodel.CityDataResponse
import retrofit2.Response

interface FirstScreenViewPresenterContract {
    interface ViewInterface {
        fun displayCitiesData(citiesList: List<CityDataResponse.City>)

    }

    interface PresenterInterface {
        fun getCitiesData()
        fun addCity(city: CityDataResponse.City)


    }
}