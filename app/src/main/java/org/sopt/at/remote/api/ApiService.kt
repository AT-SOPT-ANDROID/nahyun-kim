package org.sopt.at.remote.api

import org.sopt.at.remote.api.interceptor.AuthType
import org.sopt.at.remote.base.BaseResponse
import org.sopt.at.remote.model.MyNicknameResponse
import org.sopt.at.remote.model.NicknameEditRequest
import org.sopt.at.remote.model.SignInRequest
import org.sopt.at.remote.model.SignInResponse
import org.sopt.at.remote.model.SignUpRequest
import org.sopt.at.remote.model.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Tag

interface ApiService {
    // 회원가입
    @POST("/api/v1/auth/signup")
    suspend fun postSignUp(
        @Body signUpRequest: SignUpRequest,
        @Tag authType: AuthType = AuthType.NO_AUTH
    ): SignUpResponse

    // 로그인
    @POST("/api/v1/auth/signin")
    suspend fun postSignIn(
        @Body signInRequest: SignInRequest,
        @Tag authType: AuthType = AuthType.NO_AUTH
    ): SignInResponse

    // 내 닉네임 조회
    @GET("/api/v1/users/me")
    suspend fun getMyNickname(): MyNicknameResponse

    // 닉네임 수정
    @PATCH("/api/v1/users")
    suspend fun patchNickname(
        @Body request: NicknameEditRequest
    ): BaseResponse
}