package com.hellohasan.weatherappmvvm.network

import com.hellohasan.weatherappmvvm.features.weather_info_show.model.data_class.WeatherInfoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("weather")
    fun callApiForWeatherInfo(@Query("id") cityId: Int): Call<WeatherInfoResponse>
}