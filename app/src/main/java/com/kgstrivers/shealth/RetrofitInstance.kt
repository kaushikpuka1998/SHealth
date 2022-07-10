package com.kgstrivers.shealth

import com.squareup.okhttp.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object{

        val BASE_URL = "http://nodeapi-env.eba-vsznnvpe.ap-south-1.elasticbeanstalk.com/"
        fun getRetroFitInstance():Retrofit{


            ///For showing the data of request and response

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val client = okhttp3.OkHttpClient.Builder()
            client.addInterceptor(logging)
            return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        }
    }
}