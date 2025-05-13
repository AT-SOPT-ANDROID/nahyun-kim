package org.sopt.at.remote.api

import org.sopt.at.remote.base.BaseResponse
import org.sopt.at.remote.model.MyNicknameResponse
import org.sopt.at.remote.model.NicknameEditRequest
import org.sopt.at.remote.model.NicknamesResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Query

interface UserApiService {
    // 내 닉네임 조회
    @GET("/api/v1/users/me")
    suspend fun getMyNickname(): MyNicknameResponse

    // 닉네임 수정
    @PATCH("/api/v1/users")
    suspend fun patchNickname(
        @Body request: NicknameEditRequest
    ): BaseResponse

    // 닉네임 조회 (검색)
    @GET("/api/v1/users")
    suspend fun getNicknames(
        @Query("keyword") searchNickname: String
    ): NicknamesResponse
}