package org.sopt.at.data.remote

import org.sopt.at.remote.base.BaseResponse
import org.sopt.at.remote.model.MyNicknameResponse
import org.sopt.at.remote.model.NicknamesResponse

interface UserRemoteDataSource {

    suspend fun getMyNickname(): MyNicknameResponse

    suspend fun patchMyNickname(
        nickname: String
    ): BaseResponse

    suspend fun getNicknames(
        searchNickname: String
    ): NicknamesResponse
}