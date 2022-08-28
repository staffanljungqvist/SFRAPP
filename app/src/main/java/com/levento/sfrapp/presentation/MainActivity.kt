package com.levento.sfrapp.presentation


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.*
import com.levento.sfrapp.ui.theme.SFRAPPTheme
import dagger.hilt.android.AndroidEntryPoint
import com.levento.sfrapp.presentation.components.SplashScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.load()

        setContent {

            val isLoading by viewModel.isLoading.collectAsState()

            SFRAPPTheme {
                if (isLoading) {
                    SplashScreen()
                } else MainView(viewModel)
            }
        }
    }
}


