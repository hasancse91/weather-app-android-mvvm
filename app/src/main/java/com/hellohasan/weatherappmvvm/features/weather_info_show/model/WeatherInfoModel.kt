package com.hellohasan.weatherappmvvm.features.weather_info_show.model

import com.hellohasan.weatherappmvvm.features.weather_info_show.model.data_class.City
import com.hellohasan.weatherappmvvm.features.weather_info_show.model.data_class.WeatherData

interface WeatherInfoModel {
    fun getCityList(modelCallback: ModelCallback<List<City>>)
    fun getWeatherInfo(cityId: Int, modelCallback: ModelCallback<WeatherData>)
}