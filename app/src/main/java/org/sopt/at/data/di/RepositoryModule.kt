package org.sopt.at.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.at.data.impl.AuthRepositoryImpl
import org.sopt.at.data.impl.UserRepositoryImpl
import org.sopt.at.domain.repository.AuthRepository
import org.sopt.at.domain.repository.UserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(repo: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(repo: UserRepositoryImpl): UserRepository
}