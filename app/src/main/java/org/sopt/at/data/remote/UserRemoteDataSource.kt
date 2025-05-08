package org.sopt.at.data.remote

import org.sopt.at.domain.model.User
import org.sopt.at.remote.base.BaseResponse

interface UserRemoteDataSource {

    suspend fun postSignUp(
        userInfo: User
    ): BaseResponse
}