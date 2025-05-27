package org.sopt.at.remote.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.at.data.remote.AuthRemoteDataSource
import org.sopt.at.data.remote.UserRemoteDataSource
import org.sopt.at.remote.impl.AuthRemoteDataSourceImpl
import org.sopt.at.remote.impl.UserRemoteDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RemoteDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindAuthRemoteDataSource(
        source: AuthRemoteDataSourceImpl
    ): AuthRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindUserRemoteDataSource(
        source: UserRemoteDataSourceImpl
    ): UserRemoteDataSource
}