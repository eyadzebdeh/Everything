package com.eyad.everything.di.module

import android.content.Context
import com.eyad.everything.BuildConfig
import com.eyad.everything.data.network.EverythingApi
import com.eyad.everything.data.network.EverythingService
import com.eyad.everything.data.network.client.EverythingClient
import com.eyad.everything.presentation.main.WrongKeyActivity
import com.eyad.networking.client.interceptor.AuthorizationInterceptor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideGson() = Gson()

    @Singleton
    @Provides
    fun provideOkHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.HEADERS
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @Singleton
    @Provides
    fun provideAuthorizationInterceptor(context: Context): AuthorizationInterceptor {
        return AuthorizationInterceptor(
            context,
            BuildConfig.API_KEY,
            WrongKeyActivity::class
        )
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        okHttpLoggingInterceptor: HttpLoggingInterceptor,
        authorizationInterceptor: AuthorizationInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authorizationInterceptor)
            .addInterceptor(okHttpLoggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitBuilder(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideEverythingClient(retrofitBuilder: Retrofit.Builder) =
        EverythingClient(retrofitBuilder)

    @Singleton
    @Provides
    fun provideEverythingService(shareClient: EverythingClient): EverythingService {
        return shareClient.build().create(EverythingService::class.java)
    }

    @Provides
    @Singleton
    fun provideEverythingApi(everythingService: EverythingService) =
        EverythingApi(everythingService)

}