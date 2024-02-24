package com.thelazybattley.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thelazybattley.common.enums.QuizType
import com.thelazybattley.data.local.entity.QuestionEntity

@Dao
interface QuestionsDao {
    @Query("SELECT * FROM questionentity WHERE quizType = :quizType")
    fun getAll(quizType: QuizType) : List<QuestionEntity>

    @Query("SELECT * FROM questionentity WHERE category = :category")
    fun getQuestionsByCategory(category: String): List<QuestionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(questions: List<QuestionEntity>)

    @Query("DELETE FROM questionentity")
    fun clearAll()
}
