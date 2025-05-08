package org.sopt.at.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.at.remote.base.BaseResponse

/**  회원가입 */
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

/** 로그인 */
@Serializable
data class SignInRequest(
    @SerialName("loginId")
    val id: String,
    @SerialName("password")
    val password: String,
)

@Serializable
data class SignInResponse(
    @SerialName("data")
    val result: SignInResult
) : BaseResponse()

@Serializable
data class SignInResult(
    val userId: Long
)

/** 닉네임 */
@Serializable
data class MyNicknameResponse(
    @SerialName("data")
    val result: NicknameResult
) : BaseResponse()

@Serializable
data class NicknameResult(
    val nickname: String
)
