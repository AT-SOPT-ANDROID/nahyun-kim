package org.sopt.at.local.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.at.data.local.UserLocalDataSource
import org.sopt.at.local.impl.UserLocalDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class LocalDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindUserLocalDataSource(
        source: UserLocalDataSourceImpl
    ): UserLocalDataSource
}