package org.sopt.at.remote.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.sopt.at.data.remote.UserRemoteDataSource
import org.sopt.at.domain.model.User
import org.sopt.at.remote.ErrorHandler.handleError
import org.sopt.at.remote.RemoteMapper.toDTO
import org.sopt.at.remote.api.ApiService
import org.sopt.at.remote.base.BaseResponse
import org.sopt.at.remote.model.MyNicknameResponse
import org.sopt.at.remote.model.NicknameEditRequest
import org.sopt.at.remote.model.NicknameResult
import org.sopt.at.remote.model.NicknamesResponse
import org.sopt.at.remote.model.NicknamesResult
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
            }.onFailure { exception ->
                response = exception.handleError()
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
                //TODO: 에러 처리
            }
        }
        return response
    }

    override suspend fun getMyNickname(): MyNicknameResponse {
        var response = MyNicknameResponse(result = NicknameResult(""))
        withContext(Dispatchers.IO) {
            runCatching {
                apiService.getMyNickname()
            }.onSuccess {
                response = it
            }.onFailure { exception ->
                //TODO: 에러 처리
            }
        }
        return response
    }

    override suspend fun patchMyNickname(nickname: String): BaseResponse {
        var response = BaseResponse()
        withContext(Dispatchers.IO) {
            runCatching {
                apiService.patchNickname(
                    NicknameEditRequest(
                        nickname = nickname
                    )
                )
            }.onSuccess {
                response = it
            }.onFailure { exception ->
                response = exception.handleError()
            }
        }
        return response
    }

    override suspend fun getNicknames(searchNickname: String): NicknamesResponse {
        var response = NicknamesResponse(result = NicknamesResult(listOf()))
        withContext(Dispatchers.IO) {
            runCatching {
                apiService.getNicknames(searchNickname)
            }.onSuccess {
                val hi = it
                response = it
            }.onFailure { exception ->
                //TODO: 에러 처리
            }
        }
        return response
    }
}