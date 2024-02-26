package com.thelazybattley.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thelazybattley.common.enums.QuizType
import com.thelazybattley.data.local.entity.AnsweredQuestionEntity
import com.thelazybattley.data.local.entity.QuestionEntity

@Dao
interface QuestionsDao {
    @Query("SELECT * FROM questionentity WHERE quizType = :quizType")
    fun getAllQuestions(quizType: QuizType) : List<QuestionEntity>

    @Query("SELECT * FROM questionentity WHERE category = :category AND quizType = :quizType ")
    fun getQuestionsByCategory(category: String, quizType: QuizType): List<QuestionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuestions(questions: List<QuestionEntity>)

    @Query("DELETE FROM questionentity")
    fun clearQuestions()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAnsweredQuestion(answeredQuestionEntity: AnsweredQuestionEntity)

    @Query("SELECT * FROM answeredquestionentity")
    fun getAllAnsweredQuestions() : List<AnsweredQuestionEntity>
}
