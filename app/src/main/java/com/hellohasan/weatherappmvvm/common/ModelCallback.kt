package com.hellohasan.weatherappmvvm.common

interface ModelCallback<T> {
    fun onSuccess(data: T)
    fun onError(throwable: Throwable)
}