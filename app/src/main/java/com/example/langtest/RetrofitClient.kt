package com.example.langtest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://libretranslate.com/translate/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: LibreTranslateApi by lazy {
        retrofit.create(LibreTranslateApi::class.java)
    }
}
