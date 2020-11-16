package com.eyad.networking.client.annotation

/**
 * X API key related.
 * Annotate API call method with this annotation if it must include an x_api_key.
 * Custom authentication interceptor will add the key based on the existence of this annotation.
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class ApiKey {

    companion object {
        const val KEY_NAME = "api_key"
    }
}