package com.example.coroutine.data.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.coroutine.data.di.Constant.MOVIE_BASE_URL
import com.example.coroutine.data.remore.detailsAPI
import com.example.coroutine.data.remore.movieAPI
import com.example.coroutine.data.remore.searchMovieApi
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

object networkmodel {
    @Module
    @InstallIn(SingletonComponent::class)
    object NetworkModule {

        @Provides
        @Singleton
        fun provideGson(): Gson = Gson()

        @Provides
        @Singleton
        fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory =
            GsonConverterFactory.create(gson)

        @Provides
        @Singleton
        fun provideOkHttpClient(
            @ApplicationContext context : Context
        ): OkHttpClient {
            val httpclient = OkHttpClient.Builder()
                .addInterceptor(ChuckerInterceptor(context))
                .connectTimeout(120L, TimeUnit.SECONDS)
                .readTimeout(120L, TimeUnit.SECONDS)
                .writeTimeout(120L, TimeUnit.SECONDS)
            return httpclient.build()
        }
        @Provides
        @Singleton
        fun provideRetrofit(
            okhttpClient: OkHttpClient,
            converterFactory: GsonConverterFactory,
        ): Retrofit {
            return Retrofit.Builder()
                .baseUrl(MOVIE_BASE_URL)
                .client(okhttpClient)
                .addConverterFactory(converterFactory)
                .build()
        }

        @Singleton
        @Provides
        fun provideMovieApiService(
            retrofit: Retrofit
        ): movieAPI = retrofit.create(
            movieAPI::class.java
        )

        @Singleton
        @Provides
        fun providedetailsAPIService(
            retrofit: Retrofit
        ): detailsAPI = retrofit.create(
            detailsAPI::class.java
        )
        @Singleton
        @Provides
        fun providesearchMovieApiService(
            retrofit: Retrofit
        ): searchMovieApi = retrofit.create(
            searchMovieApi::class.java
        )
    }
}