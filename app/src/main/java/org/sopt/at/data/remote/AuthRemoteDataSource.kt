package org.sopt.at.data.remote

import org.sopt.at.domain.model.User
import org.sopt.at.remote.base.BaseResponse
import org.sopt.at.remote.model.SignInResponse

interface AuthRemoteDataSource {

    suspend fun postSignUp(
        userInfo: User
    ): BaseResponse

    suspend fun postSignIn(
        id: String,
        password: String
    ): SignInResponse
}