package com.thelazybattley.data.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.thelazybattley.data.local.entity.QuestionEntity

class QuestionEntityConverter {

    @TypeConverter
    fun fromItem(item: List<QuestionEntity>): String {
        val gson = Gson()
        return gson.toJson(item)
    }

    @TypeConverter
    fun toItemList(item: String): List<QuestionEntity> {
        val gson = Gson()
        val itemType = object : TypeToken<List<QuestionEntity>>() {}.type
        return gson.fromJson(item, itemType)
    }
}
