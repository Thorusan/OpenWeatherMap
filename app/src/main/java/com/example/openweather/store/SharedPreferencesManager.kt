package com.example.kamino.store

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import com.example.kamino.common.Constants
import com.example.openweather.repositories.WeatherApiService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class SharedPreferencesManager(activity: Activity) {

   /* companion object {


        fun saveCityToSharedPreferences(): WeatherApiService {

            val sharedPref = activity.getPreferences(Context.MODE_PRIVATE) ?: return
            with(sharedPref.edit()) {
                putBoolean("likes", true)
                commit()
            }
        }
    }*/
}