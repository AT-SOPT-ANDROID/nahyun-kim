package org.sopt.at.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.at.remote.base.BaseResponse

// 회원가입
@Serializable
data class SignUpRequest(
    @SerialName("loginId")
    val id: String,
    @SerialName("password")
    val password: String,
    @SerialName("nickname")
    val nickname: String,
)

@Serializable
data class SignUpResponse(
    @SerialName("data")
    val result: SignUpResult
) : BaseResponse()

@Serializable
data class SignUpResult(
    val userId: Long,
    val nickname: String
)