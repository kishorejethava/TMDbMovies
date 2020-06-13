package com.kishorejethava.moviepilot.webservice

import com.kishorejethava.moviepilot.BuildConfig
import com.kishorejethava.moviepilot.movies.model.ResMovieList
import com.kishorejethava.moviepilot.utils.Constants.BASE_URL
import com.kishorejethava.moviepilot.utils.Constants.TMDB_API_KEY
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit


interface ApiService {

    @GET("3/discover/movie?api_key=4f69028550e6e31686803cc60bb8db6b&language=en-US&sort_by=popularity.desc")
    suspend fun getMovieList(@Query("page") page : Int): ResMovieList



    companion object {
        private const val DEFAULT_TIMEOUT = 5000
        fun create(): ApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(buildHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService::class.java)
        }

        /*Config HttpClient*/
        private fun buildHttpClient(): OkHttpClient {

            //set default time out
            val builder = OkHttpClient.Builder()
            builder.connectTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.MINUTES)
                .writeTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.MINUTES)
                .readTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.MINUTES)

            // enable logging
            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                builder.addInterceptor(logging)
            } else {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.NONE
                builder.addInterceptor(logging)
            }

            builder.addInterceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                requestBuilder.addHeader("api_key", TMDB_API_KEY)
                chain.proceed(requestBuilder.build())
            }
            return builder.build()
        }
    }
}