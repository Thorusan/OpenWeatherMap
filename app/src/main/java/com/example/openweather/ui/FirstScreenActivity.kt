package com.example.openweather.ui

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.BindView
import butterknife.ButterKnife
import com.example.kamino.datamodel.CityModel
import com.example.openweather.R
import com.example.openweather.datamodel.CityDataResponse
import com.example.openweather.repositories.MemoryDataProvider
import com.example.openweather.repositories.WeatherApiService
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.disposables.Disposable


class FirstScreenActivity : AppCompatActivity(), FirstScreenViewPresenterContract.ViewInterface {


    private lateinit var model: CityModel
    private lateinit var firstScreenPresenter: FirstScreenPresenter
    private lateinit var dataProvider: MemoryDataProvider
    lateinit var disposable: Disposable
    private val REQUEST_CODE: Int = 101
    @BindView(R.id.recycler_view)
    lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView

    @BindView(R.id.btn_add_city)
    lateinit var btnAddCity: Button

    var citiesList: ArrayList<CityDataResponse.City> = arrayListOf()

    val apiService by lazy {
        WeatherApiService.create()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_screen)

        ButterKnife.bind(this)

        //callApi_getWeatherData()
        //citiesList = getCityFromSharedPreferences()

        registerListeners();

        dataProvider = MemoryDataProvider()
        model = CityModel(apiService, dataProvider)

        firstScreenPresenter = FirstScreenPresenter(this, model!!)
        firstScreenPresenter.getCitiesData()

        //callApi_getWeatherData()
    }

    override fun displayCitiesData(citiesList: List<CityDataResponse.City>) {
        if (citiesList.size > 0) {
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

    override fun onStop() {
        /*if(!disposable.isDisposed){
            disposable.dispose()
        }*/
        super.onStop()
    }

    override fun onDestroy() {
        //firstScreenPresenter;
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 101) {
            if (data != null) {
                val newCity =
                    data.getExtras().getParcelable("saved_city") as CityDataResponse.City
                firstScreenPresenter.addCity(newCity);
                firstScreenPresenter.getCitiesData()

            }

        }
        super.onActivityResult(requestCode, resultCode, data)
    }


    private fun showCityDetails(item: CityDataResponse.City) {
        val toast = Toast.makeText(
            applicationContext,
            item.name,
            Toast.LENGTH_LONG
        )
        toast.show()
    }

    private fun registerListeners() {
        // Add city (go to second screen)
        btnAddCity.setOnClickListener {
            val intent = Intent(this, SecondScreenActivity::class.java);
            intent.putExtra("citiesList", citiesList);
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    private fun getCityFromSharedPreferences(): ArrayList<CityDataResponse.City> {
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
