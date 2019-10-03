package com.example.openweather.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kamino.datamodel.CityModel
import com.example.openweather.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import kotlinx.android.synthetic.main.activity_second_screen.*
import android.preference.PreferenceManager
import android.content.SharedPreferences
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class SecondScreenActivity : AppCompatActivity() {
    var citiesList: ArrayList<CityModel> = arrayListOf<CityModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_screen)

        //ButterKnife.bind(this)
        //getCityFromSharedPref
        citiesList = getCityFromSharedPreferences()
        registerListeners()
    }

    private fun registerListeners() {
        // Add city (go to second screen)
        btn_cancel.setOnClickListener {
            this.finish()

        }

        btn_finish.setOnClickListener {
            val cityName: String = edit_city_name.text.toString()
            val cityObject = CityModel(cityName) // create l1 object of Lamp class
            citiesList.add(cityObject)
            val citiesArrayString = Gson().toJson(citiesList)
            saveCityToSharedPreferences(citiesArrayString)
            this.finish();
        }
    }

    private fun saveCityToSharedPreferences(arrayString: String) {
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(this) ?: return
        with(sharedPref.edit()) {
            putString("cities", arrayString)
            commit()
        }
    }

    private fun getCityFromSharedPreferences(): ArrayList<CityModel> {
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val gson = Gson()
        val json = prefs.getString("cities", null)
        val type = object : TypeToken<ArrayList<CityModel>>() {

        }.type
        if (json == null) {
            return ArrayList()
        }
        return gson.fromJson(json, type)
    }

}
