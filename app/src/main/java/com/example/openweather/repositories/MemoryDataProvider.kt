package com.example.openweather.repositories

import com.example.openweather.datamodel.CityDataResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MemoryDataProvider(): DataProvider {
    private var citiesList: ArrayList<CityDataResponse.City> = ArrayList<CityDataResponse.City>()

    override fun getCities(): List<CityDataResponse.City> {
        return citiesList
    }

    override fun addCity(city: CityDataResponse.City) {
        citiesList.add(city);
    }

    /*fun callApi_getWeatherData() {
        disposable = apiService.getWeatherData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->

                val responseCode = response.code()
                when (responseCode) {
                    200, 201, 202 -> {
                        System.out.println(response)
                    }
                    401 -> {
                    }
                    402 -> {
                    }
                    500 -> {
                    }
                    501 -> {
                    }
                }
            },
                { error ->
                    System.out.println(error)
                }
            )
    }*/

}