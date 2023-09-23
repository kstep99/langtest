package com.example.langtest

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LibreTranslateApi {
    @FormUrlEncoded
    @POST("translate")
    fun translateText(
        @Field("source") sourceLanguage: String,
        @Field("target") targetLanguage: String,
        @Field("text") text: String
    ): Call<TranslationResponse>
}

data class TranslationResponse(
    val translatedText: String
)
