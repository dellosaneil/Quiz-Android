package com.thelazybattley.data.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class NullableStringListConverter {


    @TypeConverter
    fun fromItemList(itemList: List<String?>): String {
        val gson = Gson()
        return gson.toJson(itemList)
    }

    @TypeConverter
    fun toItemList(nullableText: String): List<String?> {
        val gson = Gson()
        val itemType = object : TypeToken<List<String?>>() {}.type
        return gson.fromJson(nullableText, itemType)
    }
}
