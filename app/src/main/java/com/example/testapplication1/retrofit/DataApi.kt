package com.example.testapplication1.retrofit

import retrofit2.http.GET

interface DataApi {
    @GET("products/1")//для примера берется первый продукт с сайта данных
    suspend fun getDataById(): Data
}