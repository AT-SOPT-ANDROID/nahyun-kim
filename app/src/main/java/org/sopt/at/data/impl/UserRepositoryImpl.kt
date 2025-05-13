package org.sopt.at.data.impl

import org.sopt.at.data.remote.UserRemoteDataSource
import org.sopt.at.domain.repository.UserRepository
import org.sopt.at.remote.base.BaseResponse
import org.sopt.at.remote.model.MyNicknameResponse
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource,
): UserRepository {

    override suspend fun getMyNickname(): MyNicknameResponse {
        return userRemoteDataSource.getMyNickname()
    }

    override suspend fun editNickname(nickname: String): BaseResponse {
        return userRemoteDataSource.patchMyNickname(nickname)
    }

    override suspend fun searchNickname(searchWord: String): List<String> {
        return userRemoteDataSource.getNicknames(searchWord).result.nicknameList
    }
}