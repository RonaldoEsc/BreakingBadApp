package com.hrec.miappdebreakingbad.retrofit

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import java.util.concurrent.TimeUnit

interface IWS {

    @Headers("Content-Type:application/json")
    @GET("api/characters")
    fun getCharacterList(): Call<JsonArray>

    companion object {
        private const val API_URL_BASE = "https://www.breakingbadapi.com/"
        fun create(): IWS {
            val logginInterceptor = HttpLoggingInterceptor()
            logginInterceptor.level = HttpLoggingInterceptor.Level.HEADERS

            val okHttpClient = OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(logginInterceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(API_URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(IWS::class.java)
        }
    }
}