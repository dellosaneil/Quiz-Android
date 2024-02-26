package com.thelazybattley.data.di

import com.thelazybattley.domain.local.GetAllQuestionsUseCase
import com.thelazybattley.domain.local.GetAllQuizResultsUseCase
import com.thelazybattley.domain.local.GetQuestionsByCategoryUseCase
import com.thelazybattley.domain.local.InsertAllQuestionsUseCase
import com.thelazybattley.domain.local.InsertCorrectAnsweredQuestionsUseCase
import com.thelazybattley.domain.local.InsertQuizResultUseCase
import com.thelazybattley.domain.local.impl.GetAllQuestionsUseCaseImpl
import com.thelazybattley.domain.local.impl.GetAllQuizResultsUseCaseImpl
import com.thelazybattley.domain.local.impl.GetQuestionsByCategoryUseCaseImpl
import com.thelazybattley.domain.local.impl.InsertAllQuestionsUseCaseImpl
import com.thelazybattley.domain.local.impl.InsertCorrectAnsweredQuestionsUseCaseImpl
import com.thelazybattley.domain.local.impl.InsertQuizResultUseCaseImpl
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
    abstract fun bindsGetCategoryDetailsUseCase(impl: GetCategoryDetailsUseCaseImpl): GetCategoryDetailsUseCase

    @Binds
    abstract fun bindsLocalGetAllQuestionsUseCase(impl: GetAllQuestionsUseCaseImpl): GetAllQuestionsUseCase

    @Binds
    abstract fun bindsInsertAllQuestionsUseCase(impl: InsertAllQuestionsUseCaseImpl): InsertAllQuestionsUseCase

    @Binds
    abstract fun bindsGetQuestionsByCategory(impl: GetQuestionsByCategoryUseCaseImpl): GetQuestionsByCategoryUseCase

    @Binds
    abstract fun bindsInsertQuizResultUseCase(impl: InsertQuizResultUseCaseImpl): InsertQuizResultUseCase

    @Binds
    abstract fun bindsGetAllQuizResults(impl: GetAllQuizResultsUseCaseImpl): GetAllQuizResultsUseCase

    @Binds
    abstract fun bindsInsertCorrectAnsweredQuestionsUseCase(impl: InsertCorrectAnsweredQuestionsUseCaseImpl) : InsertCorrectAnsweredQuestionsUseCase
}
