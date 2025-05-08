package org.sopt.at.remote.impl

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.sopt.at.data.remote.UserRemoteDataSource
import org.sopt.at.domain.model.User
import org.sopt.at.remote.ErrorHandler.handleError
import org.sopt.at.remote.RemoteMapper.toDTO
import org.sopt.at.remote.api.ApiService
import org.sopt.at.remote.base.BaseResponse
import org.sopt.at.remote.model.SignInRequest
import org.sopt.at.remote.model.SignInResponse
import org.sopt.at.remote.model.SignInResult
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
): UserRemoteDataSource {
    override suspend fun postSignUp(userInfo: User): BaseResponse {
        var response = BaseResponse()
        withContext(Dispatchers.IO) {
            runCatching {
                apiService.postSignUp(
                    userInfo.toDTO()
                )
            }.onSuccess {
                response = it
                Log.d("UserRemoteDataSourceImpl", "SignUp success: $it")
            }.onFailure { exception ->
                response = exception.handleError()
                Log.d("UserRemoteDataSourceImpl", "SignUp fail: ${response.message}}")
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
                apiService.postSignIn(
                    SignInRequest(id, password)
                )
            }.onSuccess {
                response = it
            }.onFailure { exception ->
                Log.d("UserRemoteDataSourceImpl", "SignIn fail: ${exception.handleError()}}")
            }
        }
        return response
    }
}