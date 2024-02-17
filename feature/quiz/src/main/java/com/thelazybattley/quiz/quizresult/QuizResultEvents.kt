package com.thelazybattley.quiz.quizresult

import com.thelazybattley.common.base.BaseEvents
import com.thelazybattley.common.model.QuizDetailsState

sealed class QuizResultEvents : BaseEvents {

    data object OnCloseButtonClickedEvent: QuizResultEvents()

    data class OnReviewQuestionsClicked(val quizDetailsState: QuizDetailsState) : QuizResultEvents()
}
