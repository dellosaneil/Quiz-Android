package com.thelazybattley.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thelazybattley.common.enums.QuizType
import com.thelazybattley.data.local.entity.PermanentAnsweredQuestionEntity
import com.thelazybattley.data.local.entity.QuestionEntity
import com.thelazybattley.data.local.entity.TempAnsweredQuestionEntity

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
    fun insertTempAnsweredQuestion(tempAnsweredQuestionEntity: TempAnsweredQuestionEntity)

    @Query("DELETE FROM tempansweredquestionentity WHERE id in (:questionIds)")
    fun deleteTempAnsweredQuestions(questionIds: List<Int>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPermanentAnsweredQuestion(entity: PermanentAnsweredQuestionEntity)

    @Query("SELECT * FROM permanentansweredquestionentity")
    fun getAllPermanentAnsweredQuestions() : List<PermanentAnsweredQuestionEntity>

}
