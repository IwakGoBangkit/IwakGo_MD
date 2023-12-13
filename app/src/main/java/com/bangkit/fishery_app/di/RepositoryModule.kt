package com.bangkit.fishery_app.di

import com.bangkit.fishery_app.data.repository.AuthRepository
import com.bangkit.fishery_app.data.repository.AuthRepositoryImpl
import com.bangkit.fishery_app.data.repository.FishRepository
import com.bangkit.fishery_app.data.repository.FishRepositoryImpl
import com.bangkit.fishery_app.data.repository.PostRepository
import com.bangkit.fishery_app.data.repository.PostRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun provideAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun providePostRepository(postRepositoryImpl: PostRepositoryImpl): PostRepository

    @Binds
    @Singleton
    abstract fun provideFishRepository(fishRepositoryImpl: FishRepositoryImpl): FishRepository
}