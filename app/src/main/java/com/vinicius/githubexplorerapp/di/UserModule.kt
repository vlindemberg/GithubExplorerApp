package com.vinicius.githubexplorerapp.di

import com.vinicius.githubexplorerapp.data.datasource.UserRemoteDataSource
import com.vinicius.githubexplorerapp.data.datasource.UserRemoteDataSourceImpl
import com.vinicius.githubexplorerapp.data.repository.UserRepositoryImpl
import com.vinicius.githubexplorerapp.data.service.UserService
import com.vinicius.githubexplorerapp.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create

@InstallIn(ViewModelComponent::class)
@Module
interface UserModule {

    @Binds
    fun bindUserRepository(userRepository: UserRepositoryImpl): UserRepository

    @Binds
    fun bindUserRemoteDataSource(userRemoteDataSource: UserRemoteDataSourceImpl): UserRemoteDataSource

}

@InstallIn(SingletonComponent::class)
@Module
object UserNetworkingModule {

    @Provides
    fun providesUserService(retrofit: Retrofit): UserService {
        return retrofit.create()
    }
}