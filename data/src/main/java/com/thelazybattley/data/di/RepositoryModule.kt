package com.thelazybattley.data.di

import com.thelazybattley.data.network.QuizRepositoryImpl
import com.thelazybattley.domain.network.QuizRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsRepository(impl: QuizRepositoryImpl) : QuizRepository

}
