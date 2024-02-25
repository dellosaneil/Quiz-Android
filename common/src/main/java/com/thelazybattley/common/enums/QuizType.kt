package com.thelazybattley.common.enums

enum class QuizType(
    val type: String
) {
    NOLI_ME_TANGERE(type = "noli-me-tangere"),
    EL_FILI(type = "el-fili"),
    LIFE_OF_RIZAL(type = "life-of-rizal");

    companion object {
        fun toQuizType(type: String) = run {
            when(type) {
                NOLI_ME_TANGERE.type -> NOLI_ME_TANGERE
                EL_FILI.type -> EL_FILI
                else -> LIFE_OF_RIZAL
            }
        }
    }
}
