package com.thelazybattley.domain.local

import com.thelazybattley.domain.model.Question

interface InsertAllQuestionsUseCase {
    suspend operator fun invoke(questions: List<Question>) : Result<Unit>
}
