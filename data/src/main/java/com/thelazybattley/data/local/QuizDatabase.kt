package com.thelazybattley.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.thelazybattley.data.local.converters.StringListConverter
import com.thelazybattley.data.local.dao.QuestionsDao
import com.thelazybattley.data.local.entity.QuestionEntity

@Database(entities = [QuestionEntity::class], version = 1)
@TypeConverters(StringListConverter::class)
abstract class QuizDatabase : RoomDatabase(){

    abstract fun questionsDao() : QuestionsDao
}
