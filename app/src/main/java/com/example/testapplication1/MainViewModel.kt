package com.example.testapplication1

import android.app.Application
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainViewModel(application: Application) : AndroidViewModel(application){

    var title = MutableLiveData<String>()
    var photo = MutableLiveData<Bitmap>()
    val retrofit = Retrofit.Builder()//сайт данных
        .baseUrl("https://dummyjson.com")
        .addConverterFactory(GsonConverterFactory.create()).build()
    val DataApi = retrofit.create(com.example.testapplication1.retrofit.DataApi::class.java)



    fun GetTitle(){
        CoroutineScope(Dispatchers.IO).launch {//запуск корутины из неосновного потока
            val data = DataApi.getDataById()//хранение данных с сайта, в переменных
            title.postValue(data.title)//postValue используется при запуске с неосновного потока
        }
    }
    fun GetSaveImage(bitmap: Bitmap){
        photo.value = bitmap
    }

    fun SetTitle(string: String){
        title.value = string//основной поток
    }

    override fun onCleared() {
        Log.e("Проверка на onCleared","AHAHHAHAHAH")
    }
}