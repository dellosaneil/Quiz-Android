package com.thelazybattley.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.thelazybattley.data.local.entity.QuizResultEntity


@Dao
interface QuizResultDao {

    @Query("SELECT * FROM quizresultentity")
    fun getAll(): List<QuizResultEntity>

    @Query("DELETE FROM quizresultentity")
    fun clearAll()

    @Insert
    fun insertResult(quizResultEntity: QuizResultEntity)


}
