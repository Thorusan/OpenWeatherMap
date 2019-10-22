package com.example.openweather.ui

import com.example.kamino.datamodel.CityModel
import com.example.openweather.datamodel.CityDataResponse
import com.example.openweather.repositories.DataProvider
import com.example.openweather.repositories.WeatherApiService

class FirstScreenPresenter(view: FirstScreenViewPresenterContract.ViewInterface, model : CityModel):
    FirstScreenViewPresenterContract.PresenterInterface {


    private var view: FirstScreenViewPresenterContract.ViewInterface = view
    private var model: CityModel = model

    override fun getCitiesData() {
        val citiesList: List<CityDataResponse.City> = model.getCitiesFromDataProvider()
        view.displayCitiesData(citiesList);
    }

    override fun addCity(city: CityDataResponse.City) {
        model.saveCity(city);
    }
}