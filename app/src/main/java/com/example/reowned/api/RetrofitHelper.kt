package com.example.reowned.api


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitHelper {

    @Provides
    @Singleton
     fun interceptor (): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
     fun okHttpClient(): OkHttpClient {
      return  OkHttpClient
            .Builder()
            .addInterceptor(interceptor())
            .build()

    }

    @Provides
    @Singleton
    fun retrofitInstance(): Retrofit {

       return Retrofit
            .Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient())
            .build()

    }

    @Provides
    @Singleton
   fun apiCall(): NetworkService {

      return retrofitInstance().create(NetworkService::class.java)

    }


}