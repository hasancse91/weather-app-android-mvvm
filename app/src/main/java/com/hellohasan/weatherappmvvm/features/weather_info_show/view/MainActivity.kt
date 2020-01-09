package com.hellohasan.weatherappmvvm.features.weather_info_show.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.hellohasan.weatherappmvvm.R
import com.hellohasan.weatherappmvvm.features.weather_info_show.model.WeatherInfoModel
import com.hellohasan.weatherappmvvm.features.weather_info_show.model.WeatherInfoModelImpl
import com.hellohasan.weatherappmvvm.features.weather_info_show.model.data_class.City
import com.hellohasan.weatherappmvvm.features.weather_info_show.viewmodel.WeatherInfoViewModel
import kotlinx.android.synthetic.main.layout_input_part.*

class MainActivity : AppCompatActivity() {

    private val model: WeatherInfoModel = WeatherInfoModelImpl(this)
    private lateinit var viewModel: WeatherInfoViewModel
    private lateinit var cityList: List<City>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initialize ViewModel
        viewModel = ViewModelProviders.of(this).get(WeatherInfoViewModel::class.java)

        // fetch city list when Activity open
        viewModel.getCityList(model)

        // observe city list success data
        viewModel.cityListLiveData.observe(this, Observer { cityList ->
            this.cityList = cityList
            Log.e("City", cityList.size.toString())
        })

        // observe city list fetching failure
        viewModel.cityListFailureLiveData.observe(this, Observer { errorMessage ->
            Log.e("City Error", errorMessage)
        })

        // observe weather info fetching success
        viewModel.weatherInfoLiveData.observe(this, Observer { weatherData ->
            Log.e("Weather Data", weatherData.toString())
        })

        // observe weather info fetching failure
        viewModel.weatherInfoFailureLiveData.observe(this, Observer { errorMessage ->
            Log.e("Weather Error", errorMessage)
        })

        // fetch data when button clicked
        btn_view_weather.setOnClickListener {
            val selectedCityId =0 //cityList[spinner.selectedItemPosition].id
            viewModel.getWeatherInfo(selectedCityId, model)
        }
    }
}
