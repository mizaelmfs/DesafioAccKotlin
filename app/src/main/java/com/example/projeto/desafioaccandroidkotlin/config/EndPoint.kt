package com.example.projeto.desafioaccandroidkotlin.config

import com.example.projeto.desafioaccandroidkotlin.page.fragment.menu.model.MenuModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface EndPoint {

    @GET("products.json")
    fun getMenu(): Call<MenuModel>

    object RetrofitConfig  {
        var retrofit = Retrofit.Builder()
            .baseUrl("https://desafio-mobility.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}