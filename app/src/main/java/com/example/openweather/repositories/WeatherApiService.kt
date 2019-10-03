package com.example.openweather.repositories

import com.example.kamino.common.Constants.Companion.BASE_URL
import com.example.kamino.datamodel.WeatherModel
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface WeatherApiService {
    @GET(BASE_URL)
    fun getKaminoPlanet(): Observable<Response<WeatherModel.KaminoPlanet>>



    companion object {
        fun create(): WeatherApiService {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create()
                )
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(WeatherApiService::class.java)
        }
    }
}



