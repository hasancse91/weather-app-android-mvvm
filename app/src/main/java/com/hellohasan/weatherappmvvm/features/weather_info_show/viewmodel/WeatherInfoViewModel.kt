package com.hellohasan.weatherappmvvm.features.weather_info_show.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hellohasan.weatherappmvvm.features.weather_info_show.model.ModelCallback
import com.hellohasan.weatherappmvvm.features.weather_info_show.model.WeatherInfoModel
import com.hellohasan.weatherappmvvm.features.weather_info_show.model.data_class.City
import com.hellohasan.weatherappmvvm.features.weather_info_show.model.data_class.WeatherData

class WeatherInfoViewModel(val model: WeatherInfoModel) : ViewModel() {

    /**
     * In our project, for sake for simplicity we used different LiveData for success and failure.
     * But it's not the only way. We can use a wrapper data class to implement success and failure
     * both using a single LiveData. Another good approach may be handle errors in BaseActivity.
     * For this project our objective is only understand about MVVM. So we made it easy to understand.
     */
    val cityListLiveData = MutableLiveData<List<City>>()
    val cityListFailureLiveData = MutableLiveData<String>()
    val weatherInfoLiveData = MutableLiveData<WeatherData>()
    val weatherInfoFailureLiveDataModel = MutableLiveData<String>()

    fun getCityList() {

        model.getCityList(object : ModelCallback<List<City>>{
            override fun onSuccess(data: List<City>) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onError(throwable: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

    }

    fun getWeatherInfo(cityId: Int) {

        model.getWeatherInfo(cityId, object : ModelCallback<WeatherData>{
            override fun onSuccess(data: WeatherData) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onError(throwable: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }
}