package com.thelazybattley.quiz.quizresultshistory

import androidx.lifecycle.viewModelScope
import com.thelazybattley.common.base.BaseViewModel
import com.thelazybattley.common.di.IoDispatcher
import com.thelazybattley.domain.local.GetAllQuizResultsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class QuizResultsHistoryViewModel @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val getAllQuizResultsUseCase: GetAllQuizResultsUseCase
) : BaseViewModel<QuizResultsHistoryEvents, QuizResultsHistoryUiState>() {
    override fun initialState() = QuizResultsHistoryUiState()

    init {
        getAllQuizResults()

    }

    private fun getAllQuizResults() {
        viewModelScope.launch(context = dispatcher) {
            getAllQuizResultsUseCase().fold(
                onSuccess = { results ->
                    val resultsWithPercentage = results.map { result ->
                        val correctAnswers = result
                            .chosenAnswers
                            .zip(result.answers)
                            .filter {
                                it.first == it.second
                            }.size
                        val percentage =
                            (correctAnswers.toFloat() / result.questions.size) * 100

                        result.copy(
                            percent = percentage.roundToInt(),
                            category = result.questions.first().category.toString()
                        )
                    }
                    updateState { state ->
                        state.copy(
                            quizResult = resultsWithPercentage
                        )
                    }

                },
                onFailure = {

                }
            )
        }
    }
}
