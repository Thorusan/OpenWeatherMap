package com.example.openweather.ui

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import com.example.kamino.datamodel.CityModel
import com.example.openweather.R
import com.example.openweather.datamodel.CityDataResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_second_screen.*


class SecondScreenActivity : AppCompatActivity() {
    private var citiesList: ArrayList<CityDataResponse.City> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_screen)

        //ButterKnife.bind(this)
        //getCityFromSharedPref
        getCitiesFromBundle()
        registerListeners()
    }

    private fun getCitiesFromBundle() {
        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            citiesList = bundle.getParcelableArrayList("citiesList");
        }
    }

    private fun registerListeners() {
        // Add city (go to second screen)
        btn_cancel.setOnClickListener {
            this.finish()

        }

        btn_finish.setOnClickListener {
            val cityName: String = edit_city_name.text.toString()
            val cityObject = CityDataResponse.City(cityName) // create l1 object of Lamp class
            citiesList.add(cityObject)
            val citiesArrayString = Gson().toJson(citiesList)
            //saveCityToSharedPreferences(citiesArrayString)
            val resultIntent = Intent()
            resultIntent.putExtra("saved_city", cityObject)
            setResult(101, resultIntent)

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
