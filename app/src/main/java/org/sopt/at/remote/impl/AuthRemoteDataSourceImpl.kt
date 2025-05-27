package org.sopt.at.remote.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.sopt.at.data.remote.AuthRemoteDataSource
import org.sopt.at.domain.model.User
import org.sopt.at.remote.ErrorHandler.handleError
import org.sopt.at.remote.RemoteMapper.toDTO
import org.sopt.at.remote.api.AuthApiService
import org.sopt.at.remote.base.BaseResponse
import org.sopt.at.remote.model.SignInRequest
import org.sopt.at.remote.model.SignInResponse
import org.sopt.at.remote.model.SignInResult
import timber.log.Timber
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val authApiService: AuthApiService
): AuthRemoteDataSource {
    override suspend fun postSignUp(userInfo: User): BaseResponse {
        var response = BaseResponse()
        withContext(Dispatchers.IO) {
            runCatching {
                authApiService.postSignUp(
                    userInfo.toDTO()
                )
            }.onSuccess {
                response = it
                Timber.d("postSignUp success: $it")
            }.onFailure { exception ->
                response = exception.handleError()
                Timber.d("postSignUp fail: ${response.message}")
            }
        }
        return response
    }

    override suspend fun postSignIn(
        id: String,
        password: String
    ): SignInResponse {
        var response = SignInResponse(result = SignInResult(-1))
        withContext(Dispatchers.IO) {
            runCatching {
                authApiService.postSignIn(
                    SignInRequest(id, password)
                )
            }.onSuccess {
                response = it
                Timber.d("postSignIn success: $it")
            }.onFailure { exception ->
                Timber.d("postSignIn fail: $exception")
            }
        }
        return response
    }
}