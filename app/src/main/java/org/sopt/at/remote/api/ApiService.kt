package org.sopt.at.remote.api

import org.sopt.at.remote.model.SignUpRequest
import org.sopt.at.remote.model.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    // 회원가입
    @POST("/api/v1/auth/signup")
    suspend fun postSignUp(
        @Body signUpRequest: SignUpRequest
    ): SignUpResponse
}