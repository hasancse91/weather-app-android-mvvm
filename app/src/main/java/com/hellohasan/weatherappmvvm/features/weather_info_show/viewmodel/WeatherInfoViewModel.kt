package com.hellohasan.weatherappmvvm.features.weather_info_show.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hellohasan.weatherappmvvm.common.ModelCallback
import com.hellohasan.weatherappmvvm.features.weather_info_show.model.WeatherInfoModel
import com.hellohasan.weatherappmvvm.features.weather_info_show.model.data_class.City
import com.hellohasan.weatherappmvvm.features.weather_info_show.model.data_class.WeatherData
import com.hellohasan.weatherappmvvm.features.weather_info_show.model.data_class.WeatherInfoResponse

class WeatherInfoViewModel : ViewModel() {

    /**
     * In our project, for sake for simplicity we used different LiveData for success and failure.
     * But it's not the only way. We can use a wrapper data class to implement success and failure
     * both using a single LiveData. Another good approach may be handle errors in BaseActivity.
     * For this project our objective is only understand about MVVM. So we made it easy to understand.
     */
    val cityListLiveData = MutableLiveData<List<City>>()
    val cityListFailureLiveData = MutableLiveData<String>()
    val weatherInfoLiveData = MutableLiveData<WeatherData>()
    val weatherInfoFailureLiveData = MutableLiveData<String>()

    /**We can inject the instance of Model in Constructor using dependency injection.
     * For sake of simplicity, I am ignoring it now. So we have to pass instance of model in every
     * methods of ViewModel.
     */
    fun getCityList(model: WeatherInfoModel) {

        model.getCityList(object :
            ModelCallback<List<City>> {
            override fun onSuccess(data: List<City>) {
                cityListLiveData.postValue(data)
            }

            override fun onError(throwable: Throwable) {
                cityListFailureLiveData.postValue(throwable.localizedMessage)
            }
        })

    }

    /**We can inject the instance of Model in Constructor using dependency injection.
     * For sake of simplicity, I am ignoring it now. So we have to pass instance of model in every
     * methods of ViewModel.
     */
    fun getWeatherInfo(cityId: Int, model: WeatherInfoModel) {

        model.getWeatherInfo(cityId, object :
            ModelCallback<WeatherInfoResponse> {
            override fun onSuccess(data: WeatherInfoResponse) {
                // TODO: convert WeatherInfoResponse to WeatherData object
                weatherInfoLiveData.postValue(WeatherData())
            }

            override fun onError(throwable: Throwable) {
                weatherInfoFailureLiveData.postValue(throwable.localizedMessage)
            }
        })
    }
}