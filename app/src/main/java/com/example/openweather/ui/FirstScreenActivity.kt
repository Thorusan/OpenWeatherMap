package com.example.openweather.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.BindView
import butterknife.ButterKnife
import com.example.kamino.common.Constants
import com.example.kamino.datamodel.CityModel
import com.example.openweather.R
import com.example.openweather.repositories.WeatherApiService
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class FirstScreenActivity : AppCompatActivity() {
    lateinit var disposable: Disposable
    private val REQUEST_CODE: Int = 101
    @BindView(R.id.recycler_view)
    lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView

    @BindView(R.id.btn_add_city)
    lateinit var btnAddCity: Button

    var citiesList: ArrayList<CityModel> = arrayListOf<CityModel>()

    val apiService by lazy {
        WeatherApiService.create()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_screen)

        ButterKnife.bind(this)

        //callApi_getWeatherData()
        citiesList = getCityFromSharedPreferences()
        setCitiesListAdapter()
        registerListeners();

        callApi_getWeatherData()
    }

    override fun onStop() {
        if(!disposable.isDisposed){
            disposable.dispose()
        }
        super.onStop()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 101) {
            citiesList = getCityFromSharedPreferences()
            setCitiesListAdapter()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun setCitiesListAdapter() {
        if (citiesList.size > 0 ) {
            val listAdapter = CitiesListAdapter(
                this,
                java.util.ArrayList(citiesList),
                { item -> showCityDetails(item) })
            recyclerView.setLayoutManager(LinearLayoutManager(this))
            recyclerView.setItemAnimator(DefaultItemAnimator())
            // Binds the Adapter to the RecyclerView
            recyclerView.setAdapter(listAdapter)
        }

    }

    private fun showCityDetails(item: CityModel) {
        // TODO:
    }

    private fun registerListeners() {
        // Add city (go to second screen)
        btnAddCity.setOnClickListener {
            val intent: Intent? = Intent(this, SecondScreenActivity::class.java);
            if (intent != null) {
                //intent.putStringArrayListExtra("residents", citiesList)
                startActivityForResult(intent, REQUEST_CODE);
            }
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

    fun callApi_getWeatherData() {
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
    }


}
