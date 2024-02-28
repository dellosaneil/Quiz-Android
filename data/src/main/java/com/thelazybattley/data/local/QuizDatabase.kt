package com.thelazybattley.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.thelazybattley.data.local.converters.NullableStringListConverter
import com.thelazybattley.data.local.converters.QuestionEntityConverter
import com.thelazybattley.data.local.converters.StringListConverter
import com.thelazybattley.data.local.dao.QuestionsDao
import com.thelazybattley.data.local.dao.QuizResultDao
import com.thelazybattley.data.local.entity.PermanentAnsweredQuestionEntity
import com.thelazybattley.data.local.entity.QuestionEntity
import com.thelazybattley.data.local.entity.QuizResultEntity
import com.thelazybattley.data.local.entity.TempAnsweredQuestionEntity

@Database(
    entities = [
        QuestionEntity::class,
        QuizResultEntity::class,
        TempAnsweredQuestionEntity::class,
        PermanentAnsweredQuestionEntity::class],
    version = 1
)
@TypeConverters(
    StringListConverter::class,
    QuestionEntityConverter::class,
    NullableStringListConverter::class
)
abstract class QuizDatabase : RoomDatabase() {

    abstract fun questionsDao(): QuestionsDao

    abstract fun quizResultDao(): QuizResultDao
}
