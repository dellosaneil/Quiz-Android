package com.thelazybattley.quizandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.thelazybattley.common.ui.theme.LocalColors
import com.thelazybattley.common.ui.theme.QuizAndroidTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizAndroidTheme {
                Box(modifier = Modifier.fillMaxSize()
                    .background(color = LocalColors.current.pink10))
            }
        }
    }
}
