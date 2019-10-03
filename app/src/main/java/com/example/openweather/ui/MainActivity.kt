package com.example.openweather.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import butterknife.BindView
import butterknife.ButterKnife
import com.example.openweather.R
import com.example.openweather.repositories.WeatherApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    @BindView(R.id.recycler_view)
    lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView

    val weatherApiservice by lazy {
        WeatherApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ButterKnife.bind(this)

        callApi_getWeatherData()
    }

    fun callApi_getWeatherData() {
        weatherApiservice.getKaminoPlanet()
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
    }
}
