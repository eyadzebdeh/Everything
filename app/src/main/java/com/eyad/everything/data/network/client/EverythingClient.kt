package com.eyad.everything.data.network.client

import com.eyad.everything.BuildConfig
import com.eyad.networking.client.NetworkClient
import retrofit2.Retrofit

class EverythingClient(retrofitBuilder: Retrofit.Builder) : NetworkClient(retrofitBuilder) {

    override val baseUrl = BuildConfig.NETWORK_BASE_URL
}