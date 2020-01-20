package com.hellohasan.weatherappmvvm.features.weather_info_show.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hellohasan.weatherappmvvm.common.RequestCompleteListener
import com.hellohasan.weatherappmvvm.features.weather_info_show.model.WeatherInfoShowModel
import com.hellohasan.weatherappmvvm.features.weather_info_show.model.data_class.City
import com.hellohasan.weatherappmvvm.features.weather_info_show.model.data_class.WeatherData
import com.hellohasan.weatherappmvvm.features.weather_info_show.model.data_class.WeatherInfoResponse
import com.hellohasan.weatherappmvvm.utils.kelvinToCelsius
import com.hellohasan.weatherappmvvm.utils.unixTimestampToDateTimeString
import com.hellohasan.weatherappmvvm.utils.unixTimestampToTimeString

class WeatherInfoViewModel : ViewModel() {

    /**
     * In our project, for sake for simplicity we used different LiveData for success and failure.
     * But it's not the only way. We can use a wrapper data class to implement success and failure
     * both using a single LiveData. Another good approach may be handle errors in BaseActivity.
     * For this project our objective is only understand about MVVM. So we made it easy to understand.
     */
    val cityListLiveData = MutableLiveData<MutableList<City>>()
    val cityListFailureLiveData = MutableLiveData<String>()
    val weatherInfoLiveData = MutableLiveData<WeatherData>()
    val weatherInfoFailureLiveData = MutableLiveData<String>()
    val progressBarLiveData = MutableLiveData<Boolean>()

    /**We can inject the instance of Model in Constructor using dependency injection.
     * For sake of simplicity, I am ignoring it now. So we have to pass instance of model in every
     * methods of ViewModel. Please be noted, it's not a good approach.
     */
    fun getCityList(model: WeatherInfoShowModel) {

        model.getCityList(object :
            RequestCompleteListener<MutableList<City>> {
            override fun onRequestSuccess(data: MutableList<City>) {
                cityListLiveData.postValue(data) // PUSH data to LiveData object
            }

            override fun onRequestFailed(errorMessage: String) {
                cityListFailureLiveData.postValue(errorMessage) // PUSH error message to LiveData object
            }
        })
    }

    /**We can inject the instance of Model in Constructor using dependency injection.
     * For sake of simplicity, I am ignoring it now. So we have to pass instance of model in every
     * methods of ViewModel. Pleas be noted, it's not a good approach.
     */
    fun getWeatherInfo(cityId: Int, model: WeatherInfoShowModel) {

        progressBarLiveData.postValue(true) // PUSH data to LiveData object to show progress bar

        model.getWeatherInfo(cityId, object :
            RequestCompleteListener<WeatherInfoResponse> {
            override fun onRequestSuccess(data: WeatherInfoResponse) {

                // business logic and data manipulation tasks should be done here
                val weatherData = WeatherData(
                    dateTime = data.dt.unixTimestampToDateTimeString(),
                    temperature = data.main.temp.kelvinToCelsius().toString(),
                    cityAndCountry = "${data.name}, ${data.sys.country}",
                    weatherConditionIconUrl = "http://openweathermap.org/img/w/${data.weather[0].icon}.png",
                    weatherConditionIconDescription = data.weather[0].description,
                    humidity = "${data.main.humidity}%",
                    pressure = "${data.main.pressure} mBar",
                    visibility = "${data.visibility/1000.0} KM",
                    sunrise = data.sys.sunrise.unixTimestampToTimeString(),
                    sunset = data.sys.sunset.unixTimestampToTimeString()
                )

                progressBarLiveData.postValue(false) // PUSH data to LiveData object to hide progress bar

                // After applying business logic and data manipulation, we push data to show on UI
                weatherInfoLiveData.postValue(weatherData) // PUSH data to LiveData object
            }

            override fun onRequestFailed(errorMessage: String) {
                progressBarLiveData.postValue(false) // hide progress bar
                weatherInfoFailureLiveData.postValue(errorMessage) // PUSH error message to LiveData object
            }
        })
    }
}