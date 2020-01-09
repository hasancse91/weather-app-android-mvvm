package com.hellohasan.weatherappmvvm.features.weather_info_show.model

interface ModelCallback<T> {
    fun onSuccess(data: T)
    fun onError(throwable: Throwable)
}