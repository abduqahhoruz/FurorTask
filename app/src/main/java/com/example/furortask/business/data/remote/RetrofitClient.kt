package com.example.furortask.business.data.remote

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.furortask.App
import com.example.furortask.BuildConfig
import com.example.furortask.business.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private fun buildNewRetrofit(): Retrofit {
        val client = OkHttpClient.Builder().also { client ->
            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                client.addInterceptor(logging)
            }
        }
            .addInterceptor(
                ChuckerInterceptor.Builder(App.context!!)
                    .collector(ChuckerCollector(App.context!!))
                    .maxContentLength(250000L)
                    .redactHeaders(Collections.emptySet())
                    .alwaysReadResponseBody(false)
                    .build()
            )
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private var retrofit: Retrofit? = null

    private fun buildRetrofit(): Retrofit {
        if (retrofit == null)
            retrofit = buildNewRetrofit()
        return retrofit!!
    }

    fun instanceApi(): Api {
        return buildRetrofit().create(Api::class.java)
    }

}