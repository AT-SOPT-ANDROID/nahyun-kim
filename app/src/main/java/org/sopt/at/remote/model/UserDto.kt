package org.sopt.at.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.at.remote.base.BaseResponse

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

@Serializable
data class NicknameEditRequest(
    val nickname: String
)

/** 유저 닉네임 검색 */
@Serializable
data class NicknamesResponse(
    @SerialName("data")
    val result: NicknamesResult
): BaseResponse()

@Serializable
data class NicknamesResult(
    val nicknameList: List<String>
)