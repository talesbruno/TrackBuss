package com.example.trackbuss.di

import com.example.trackbuss.data.api.SpTransApi
import com.example.trackbuss.data.repository.SpTransRepositoryImpl
import com.example.trackbuss.domain.repository.SpTransRepository
import com.example.trackbuss.utils.Constants.API_KEY
import com.example.trackbuss.utils.Constants.BASE_URL
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
class AppModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(logging: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $API_KEY")
                    .build()
                chain.proceed(request)
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieAppService(retrofit: Retrofit): SpTransApi {
        return retrofit.create(SpTransApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSpTransRepository(spTransApi: SpTransApi): SpTransRepository {
        return SpTransRepositoryImpl(spTransApi)
    }
}