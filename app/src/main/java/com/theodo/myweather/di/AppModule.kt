package com.theodo.myweather.di

import com.theodo.myweather.data.AppConstants
import com.theodo.myweather.data.api.ApiService
import com.theodo.myweather.data.datasource.WeatherDataSource
import com.theodo.myweather.data.datasource.WeatherDataSourceImplementation
import com.theodo.myweather.data.repository.WeatherRepo
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton

    fun providesRetrofit () : Retrofit {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }

        val httpClient = OkHttpClient().newBuilder().apply {
            addInterceptor(httpLoggingInterceptor)
        }

        httpClient.apply {
            readTimeout(60, TimeUnit.SECONDS)
        }

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory()).build()

        return Retrofit.Builder()
            .baseUrl(AppConstants.APP_BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesWeatherDataSource(apiService: ApiService) : WeatherDataSource {
        return WeatherDataSourceImplementation(apiService)
    }


    @Provides
    @Singleton
    fun providesWeatherRepo(weatherDataSource: WeatherDataSource) : WeatherRepo {
        return WeatherRepo(weatherDataSource)
    }
}