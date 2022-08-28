package com.softvalley.barcodescanner.network

import android.util.Log
import com.softvalley.barcodescanner.BarCodeApplication.Companion.baseUrl
import com.softvalley.barcodescanner.utils.CONSTANTS.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitClient  {


    private  var retrofitInstance: Retrofit? =null
   private  fun getRetrofit(): Retrofit?
    {


        if (retrofitInstance==null && BASE_URL!= baseUrl)
        {
            Log.e("retrofit","not created")

            BASE_URL = baseUrl
            val builder = OkHttpClient().newBuilder()
            builder.readTimeout(30, TimeUnit.SECONDS)
            builder.connectTimeout(30, TimeUnit.SECONDS)
            builder.writeTimeout(30, TimeUnit.SECONDS)
            builder.addInterceptor(getLoggingInterceptor())

            retrofitInstance= Retrofit.Builder()
                .client(builder.build())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }else
        {
            Log.e("retrofit"," created")
        }



        return retrofitInstance
    }

    fun getApi():Api
    {
        return  getRetrofit()!!.create(Api::class.java)
    }

    private fun getLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }
}