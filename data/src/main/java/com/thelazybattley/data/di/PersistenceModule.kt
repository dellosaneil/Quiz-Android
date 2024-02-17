package com.thelazybattley.data.di

import android.content.Context
import androidx.room.Room
import com.thelazybattley.data.local.QuizDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    private const val QUIZ_DB = "quiz.db"

    @Provides
    @Singleton
    fun provideQuizDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context = context,
        klass = QuizDatabase::class.java,
        name = QUIZ_DB
    ).build()

    @Provides
    @Singleton
    fun provideQuestionsDao(db: QuizDatabase) = db.questionsDao()


    @Provides
    @Singleton
    fun provideQuizResultDao(db: QuizDatabase) = db.quizResultDao()

}
