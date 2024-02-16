package com.thelazybattley.data.di

import com.thelazybattley.domain.local.GetAllQuestionsUseCase
import com.thelazybattley.domain.local.GetQuestionsByCategory
import com.thelazybattley.domain.local.InsertAllQuestionsUseCase
import com.thelazybattley.domain.local.impl.GetAllQuestionsUseCaseImpl
import com.thelazybattley.domain.local.impl.GetQuestionsByCategoryImpl
import com.thelazybattley.domain.local.impl.InsertAllQuestionsUseCaseImpl
import com.thelazybattley.domain.network.usecase.AddQuestionUseCase
import com.thelazybattley.domain.network.usecase.FetchAllQuestionsUseCase
import com.thelazybattley.domain.network.usecase.GetCategoryDetailsUseCase
import com.thelazybattley.domain.network.usecase.InsertReportedQuestionUseCase
import com.thelazybattley.domain.network.usecase.impl.AddQuestionUseCaseImpl
import com.thelazybattley.domain.network.usecase.impl.FetchAllQuestionsUseCaseImpl
import com.thelazybattley.domain.network.usecase.impl.GetCategoryDetailsUseCaseImpl
import com.thelazybattley.domain.network.usecase.impl.InsertReportedQuestionUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
abstract class UseCaseModule {

    @Binds
    abstract fun bindsNetworkGetAllQuestionsUseCase(impl: FetchAllQuestionsUseCaseImpl): FetchAllQuestionsUseCase

    @Binds
    abstract fun bindsAddQuestionUseCase(impl: AddQuestionUseCaseImpl): AddQuestionUseCase

    @Binds
    abstract fun bindsInsertReportedQuestion(impl: InsertReportedQuestionUseCaseImpl): InsertReportedQuestionUseCase

    @Binds
    abstract fun bindsFetchCategoryDetails(impl: GetCategoryDetailsUseCaseImpl): GetCategoryDetailsUseCase

    @Binds
    abstract fun bindsLocalGetAllQuestionsUseCase(impl: GetAllQuestionsUseCaseImpl): GetAllQuestionsUseCase

    @Binds
    abstract fun bindsInsertAllQuestionsUseCase(impl: InsertAllQuestionsUseCaseImpl): InsertAllQuestionsUseCase

    @Binds
    abstract fun bindsGetQuestionsByCategory(impl: GetQuestionsByCategoryImpl): GetQuestionsByCategory
}
