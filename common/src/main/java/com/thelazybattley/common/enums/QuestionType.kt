package com.thelazybattley.common.enums

enum class QuestionType {
    UNKNOWN,
    CHILDHOOD,
    RELATIONSHIP;
}

val String.toQuestionType
    get() = run {
        when (this) {
            QuestionType.CHILDHOOD.name -> QuestionType.CHILDHOOD
            QuestionType.RELATIONSHIP.name -> QuestionType.RELATIONSHIP
            else -> {
                QuestionType.UNKNOWN
            }
        }
    }
