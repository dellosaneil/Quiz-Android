package com.thelazybattley.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.thelazybattley.data.local.entity.QuestionEntity

@Dao
interface QuestionsDao {
    @Query("SELECT * FROM questionentity")
    fun getAll() : List<QuestionEntity>

    @Insert
    fun insertAll(questions: List<QuestionEntity>)

    @Query("DELETE FROM questionentity")
    fun clearAll()
}
