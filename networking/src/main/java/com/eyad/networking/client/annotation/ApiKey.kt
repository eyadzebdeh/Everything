package com.eyad.networking.client.annotation

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class ApiKey {
    companion object {
        const val KEY_NAME = "api_key"
    }
}