package com.example.openweather.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import butterknife.BindView
import butterknife.ButterKnife
import com.example.openweather.R
import com.example.openweather.repositories.WeatherApiService

class FirstScreenActivity : AppCompatActivity() {
    @BindView(R.id.recycler_view)
    lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView

    @BindView(R.id.btn_add_city)
    lateinit var btnAddCity: Button

    val weatherApiservice by lazy {
        WeatherApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_screen)

        ButterKnife.bind(this)

        //callApi_getWeatherData()
        registerListeners();


    }

    private fun registerListeners() {
        // Add city (go to second screen)
        btnAddCity.setOnClickListener {
            val intent: Intent? = Intent(this, SecondScreenActivity::class.java);
            if (intent != null) {
                //intent.putStringArrayListExtra("residents", residentsList)
                startActivity(intent);
            }
        }
    }

   /* fun callApi_getWeatherData() {
        weatherApiservice.getWeatherData()
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
