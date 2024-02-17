package com.thelazybattley.common.ext

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.stringResource
import com.thelazybattley.common.R
import com.thelazybattley.common.model.PercentageDisplay
import com.thelazybattley.common.ui.theme.colors

val Int.toPercentageDisplay
    @Composable
    @ReadOnlyComposable
    get() = run {
        when {
            this >= 90 -> PercentageDisplay(
                textColor = colors.green10,
                text = stringResource(R.string.congratulations)
            )

            this >= 80 -> PercentageDisplay(
                textColor = colors.black50,
                text = stringResource(R.string.good_job)
            )

            else -> {
                PercentageDisplay(
                    textColor = colors.red30,
                    text = stringResource(R.string.study_to_get_a_better_result)
                )
            }
        }
    }
