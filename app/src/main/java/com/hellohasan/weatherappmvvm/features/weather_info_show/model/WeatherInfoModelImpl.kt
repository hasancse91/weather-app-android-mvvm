package com.hellohasan.weatherappmvvm.features.weather_info_show.model

import android.content.Context
import com.hellohasan.weatherappmvvm.features.weather_info_show.model.data_class.City
import com.hellohasan.weatherappmvvm.features.weather_info_show.model.data_class.WeatherInfoResponse

class WeatherInfoModelImpl(context: Context): WeatherInfoModel {

    override fun getCityList(modelCallback: ModelCallback<List<City>>) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getWeatherInfo(cityId: Int, modelCallback: ModelCallback<WeatherInfoResponse>) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}