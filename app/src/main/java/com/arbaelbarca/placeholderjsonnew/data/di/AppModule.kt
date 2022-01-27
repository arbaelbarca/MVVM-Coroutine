package com.arbaelbarca.placeholderjsonnew.data.di

import com.arbaelbarca.placeholderjsonnew.data.api.ApiService
import com.arbaelbarca.placeholderjsonnew.utils.ConstVar
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideOKHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(httpLoggingInterceptor)
        builder.readTimeout(120, TimeUnit.SECONDS)
        builder.connectTimeout(120, TimeUnit.SECONDS)
        builder.writeTimeout(120, TimeUnit.SECONDS)
        return builder.build()

    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): ApiService =
        Retrofit.Builder()
            .baseUrl(ConstVar.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)


    @Singleton
    @Provides
    fun httpInterceptorLogging(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }
}