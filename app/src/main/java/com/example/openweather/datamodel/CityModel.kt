package com.example.kamino.datamodel

class CityModel {

     var name: String? = null
        get() = field
        set(value) {
            field = value
        }

    constructor(cityName: String) {
        name = cityName
    }


}