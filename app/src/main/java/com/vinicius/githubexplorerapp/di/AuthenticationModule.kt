package com.vinicius.githubexplorerapp.di

import com.vinicius.githubexplorerapp.data.datasource.AuthenticationRemoteDataSource
import com.vinicius.githubexplorerapp.data.datasource.AuthenticationRemoteDataSourceImpl
import com.vinicius.githubexplorerapp.data.repository.AuthenticationRepositoryImpl
import com.vinicius.githubexplorerapp.data.service.AuthenticationService
import com.vinicius.githubexplorerapp.domain.repository.AuthenticationRepository
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
interface AuthenticationModule {

    @Binds
    fun bindAuthenticationRepository(authenticationRepository: AuthenticationRepositoryImpl): AuthenticationRepository

    @Binds
    fun bindAuthenticationRemoteDataSource(authenticationRemoteDataSource: AuthenticationRemoteDataSourceImpl): AuthenticationRemoteDataSource

}

@InstallIn(SingletonComponent::class)
@Module
object AuthenticationNetworkingModule {

    @Provides
    fun providesAuthenticationService(retrofit: Retrofit): AuthenticationService {
        return retrofit.create()
    }
}