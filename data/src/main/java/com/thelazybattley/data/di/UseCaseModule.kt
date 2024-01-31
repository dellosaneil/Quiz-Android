package com.thelazybattley.data.di

import com.thelazybattley.domain.network.usecase.AddQuestionUseCase
import com.thelazybattley.domain.network.usecase.FetchQuestionsUseCase
import com.thelazybattley.domain.network.usecase.impl.AddQuestionUseCaseImpl
import com.thelazybattley.domain.network.usecase.impl.FetchQuestionsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
abstract class UseCaseModule {

    @Binds
    abstract fun bindsFetchQuestionsUseCase(impl: FetchQuestionsUseCaseImpl): FetchQuestionsUseCase

    @Binds
    abstract fun bindsAddQuestionUseCase(impl: AddQuestionUseCaseImpl): AddQuestionUseCase

}
