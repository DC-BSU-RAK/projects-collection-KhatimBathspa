package com.example.lenslightapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Your original Dark Colors
val AmberGold = Color(0xFFFFB703)
val Obsidian = Color(0xFF121212)
val CardDark = Color(0xFF1E1E1E)
val SubtleGray = Color(0xFFAAAAAA)

// NEW: Your Light Colors
val PaperWhite = Color(0xFFF8F9FA)
val CardLight = Color(0xFFFFFFFF)
val TextDark = Color(0xFF212529)

private val DarkColors = darkColorScheme(
    background = Obsidian,
    surface = CardDark,
    primary = AmberGold,
    onBackground = SubtleGray,
    onSurface = Color.White
)

private val LightColors = lightColorScheme(
    background = PaperWhite,
    surface = CardLight,
    primary = AmberGold,
    onBackground = TextDark,
    onSurface = TextDark
)

@Composable
fun LensLightTheme(
    darkTheme: Boolean = true, // We will control this with our switch!
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}