package com.thelazybattley.joserizalquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.thelazybattley.common.ui.theme.QuizAndroidTheme
import com.thelazybattley.joserizalquiz.navigation.AppNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizAndroidTheme {
                AppNavHost()
            }
        }
    }
}
