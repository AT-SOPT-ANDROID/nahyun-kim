package org.sopt.at.remote.api.interceptor

import jakarta.inject.Inject
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import org.sopt.at.data.local.UserLocalDataSource

class AuthInterceptor @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        val authType = request.tag(AuthType::class.java) ?: AuthType.AUTH

        if (authType == AuthType.AUTH) {
            val userId = runBlocking { userLocalDataSource.getUserId().first() }
            builder.addHeader("userId", userId.toString())
        }

        return chain.proceed(builder.build())
    }
}

enum class AuthType {
    NO_AUTH,
    AUTH
}