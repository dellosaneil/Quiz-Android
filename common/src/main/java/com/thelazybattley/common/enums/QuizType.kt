package com.thelazybattley.common.enums

enum class QuizType(
    val type: String,
    val shortName: String
) {
    NOLI_ME_TANGERE(type = "noli-me-tangere", shortName = "Noli"),
    EL_FILI(type = "el-fili", shortName = "El Fili"),
    LIFE_OF_RIZAL(type = "life-of-rizal", shortName = "Life of Rizal");

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
