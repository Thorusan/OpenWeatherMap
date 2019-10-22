package com.example.openweather.datamodel

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

open class CityDataResponse {

    data class City(
        val name: String = ""
    ) : Parcelable {
        constructor(parcel: Parcel) : this(parcel.readString()) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(name)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<City> {
            override fun createFromParcel(parcel: Parcel): City {
                return City(parcel)
            }

            override fun newArray(size: Int): Array<City?> {
                return arrayOfNulls(size)
            }
        }
    }


    data class WeatherData(
        @SerializedName("main")
        val mainData: MainData

    )

    data class MainData(
        @SerializedName("humidity")
        val humidity: String,
        @SerializedName("temp")
        val temperature: String,
        @SerializedName("description")
        val description: String
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString()
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(humidity)
            parcel.writeString(temperature)
            parcel.writeString(description)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<MainData> {
            override fun createFromParcel(parcel: Parcel): MainData {
                return MainData(parcel)
            }

            override fun newArray(size: Int): Array<MainData?> {
                return arrayOfNulls(size)
            }
        }
    }

}

