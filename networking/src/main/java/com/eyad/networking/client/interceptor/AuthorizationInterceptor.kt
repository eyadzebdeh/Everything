package com.eyad.networking.client.interceptor

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.eyad.networking.client.annotation.ApiKey
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Invocation
import kotlin.reflect.KClass

class AuthorizationInterceptor(
    private val applicationContext: Context? = null,
    private val apiKey: String?,
    private val keyFailureActivityClass: KClass<out Activity>? = null
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val invocation = request.tag(Invocation::class.java)
        val apiMethod = invocation?.method()

        val requestBuilder = request.newBuilder()

        if (apiMethod?.getAnnotation(ApiKey::class.java) != null) {
            if (apiKey != null && apiKey.isNotEmpty()) {
                val url = request.url.newBuilder().addQueryParameter(ApiKey.KEY_NAME, apiKey).build()
                requestBuilder.url(url)
            } else {
                forceLogout()
            }
        }

        return chain.proceed(requestBuilder.build())
    }

    private fun forceLogout() {
        keyFailureActivityClass?.let { activityClass ->
            applicationContext?.startActivity(
                Intent(applicationContext, activityClass.java)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            )
        }
    }

}