package com.hellohasan.weatherappmvvm.features.weather_info_show.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.hellohasan.weatherappmvvm.R
import com.hellohasan.weatherappmvvm.features.weather_info_show.model.WeatherInfoModel
import com.hellohasan.weatherappmvvm.features.weather_info_show.model.WeatherInfoModelImpl
import com.hellohasan.weatherappmvvm.features.weather_info_show.viewmodel.WeatherInfoViewModel

class MainActivity : AppCompatActivity() {

    private val model: WeatherInfoModel = WeatherInfoModelImpl(this)
    private val viewModel = WeatherInfoViewModel(model)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // observe city list success data
        viewModel.cityListLiveData.observe(this, Observer { cityList ->
            Log.d("City", cityList.size.toString())
        })

        // observe city list fetching failure
        viewModel.cityListFailureLiveData.observe(this, Observer { errorMessage ->
            Log.d("City Error", errorMessage)
        })

        // observe weather info fetching success
        viewModel.weatherInfoLiveData.observe(this, Observer { weatherData ->
            Log.d("Weather Data", weatherData.toString())
        })

        // observe weather info fetching failure
        viewModel.weatherInfoFailureLiveDataModel.observe(this, Observer { errorMessage ->
            Log.d("Weather Error", errorMessage)
        })
    }
}
