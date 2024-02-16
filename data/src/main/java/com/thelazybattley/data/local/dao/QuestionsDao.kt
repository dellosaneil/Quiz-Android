package com.thelazybattley.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thelazybattley.common.enums.QuestionCategory
import com.thelazybattley.data.local.entity.QuestionEntity

@Dao
interface QuestionsDao {
    @Query("SELECT * FROM questionentity")
    fun getAll() : List<QuestionEntity>

    @Query("SELECT * FROM questionentity WHERE category = :category")
    fun getQuestionsByCategory(category: QuestionCategory): List<QuestionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(questions: List<QuestionEntity>)

    @Query("DELETE FROM questionentity")
    fun clearAll()
}
