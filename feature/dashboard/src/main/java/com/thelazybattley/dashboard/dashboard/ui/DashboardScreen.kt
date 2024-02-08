package com.thelazybattley.dashboard.dashboard.ui

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavOptions
import com.thelazybattley.common.model.AppScreens

@Composable
fun DashboardScreen(
    navigate: (String, NavOptions?) -> Unit
) {
    Button(
        onClick = {
            navigate(AppScreens.QuizScreen.route, null)
        }
    ) {
        Text(text = "Quiz")
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewDashboardScreen() {

}
