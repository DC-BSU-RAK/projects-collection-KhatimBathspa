package com.sweetscience.matrix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.sweetscience.matrix.logic.CalculatorViewModel
import com.sweetscience.matrix.theme.SweetScienceTheme
import com.sweetscience.matrix.ui.SweetScienceScreen

/**
 * Single-Activity entry point.
 *
 * Responsibilities:
 *   1. Install the Splash Screen (modern androidx.core.splashscreen API).
 *   2. Enable edge-to-edge rendering so the dark background fills the entire display.
 *   3. Provide the [CalculatorViewModel] to the Compose tree.
 */
class MainActivity : ComponentActivity() {

    // ViewModel survives configuration changes automatically via viewModels()
    private val viewModel: CalculatorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        // Splash Screen — must be called BEFORE super.onCreate()
        val splashScreen = installSplashScreen()
        // Keep the splash on screen while app is loading (no async work needed here)
        splashScreen.setKeepOnScreenCondition { false }

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            SweetScienceTheme {
                SweetScienceScreen(
                    viewModel = viewModel,
                )
            }
        }
    }
}
