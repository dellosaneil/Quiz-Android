package com.thelazybattley.common.enums


import com.thelazybattley.common.ext.toTitleCase

enum class QuestionCategory {
    RELATIONSHIP,
    CHILDHOOD,
    UNKNOWN,
    ADULTHOOD,
    PEOPLE,
    IMPORTANT_PLACES;

    override fun toString(): String {
        return toTitleCase()
    }
}

val String.toQuestionCategory
    get() = run {
        when (this) {
            QuestionCategory.CHILDHOOD.name -> QuestionCategory.CHILDHOOD
            QuestionCategory.RELATIONSHIP.name -> QuestionCategory.RELATIONSHIP
            QuestionCategory.ADULTHOOD.name -> QuestionCategory.ADULTHOOD
            QuestionCategory.PEOPLE.name -> QuestionCategory.PEOPLE
            QuestionCategory.IMPORTANT_PLACES.name -> QuestionCategory.IMPORTANT_PLACES
            else -> {
                QuestionCategory.UNKNOWN
            }
        }
    }