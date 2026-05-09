package com.sweetscience.matrix.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Typography
// Using system default sans-serif families available without custom font files.
// Replace FontFamily.Default with a loaded FontFamily if you add .ttf assets.

val DisplayFont  = FontFamily.Default
val BodyFont     = FontFamily.Default

val SweetScienceTypography = androidx.compose.material3.Typography(
    displayLarge = TextStyle(
        fontFamily   = DisplayFont,
        fontWeight   = FontWeight.Black,
        fontSize     = 36.sp,
        letterSpacing = 4.sp,
        color        = TextPrimary
    ),
    displayMedium = TextStyle(
        fontFamily   = DisplayFont,
        fontWeight   = FontWeight.Bold,
        fontSize     = 24.sp,
        letterSpacing = 2.sp,
        color        = TextPrimary
    ),
    titleLarge = TextStyle(
        fontFamily = BodyFont,
        fontWeight = FontWeight.Bold,
        fontSize   = 18.sp,
        color      = TextPrimary
    ),
    bodyLarge = TextStyle(
        fontFamily  = BodyFont,
        fontWeight  = FontWeight.Normal,
        fontSize    = 15.sp,
        lineHeight  = 22.sp,
        color       = TextPrimary
    ),
    bodyMedium = TextStyle(
        fontFamily = BodyFont,
        fontSize   = 13.sp,
        color      = TextSecondary
    ),
    labelLarge = TextStyle(
        fontFamily   = BodyFont,
        fontWeight   = FontWeight.ExtraBold,
        fontSize     = 14.sp,
        letterSpacing = 1.5.sp
    )
)

// Colour scheme

private val DarkColors = darkColorScheme(
    primary          = BloodRed,
    onPrimary        = TextPrimary,
    secondary        = GoldBell,
    onSecondary      = GymBlack,
    tertiary         = IceBlue,
    background       = GymBlack,
    onBackground     = TextPrimary,
    surface          = RingMat,
    onSurface        = TextPrimary,
    surfaceVariant   = CornerSteel,
    onSurfaceVariant = TextSecondary,
    outline          = ChainLink
)

// Theme entry-point

@Composable
fun SweetScienceTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColors,
        typography  = SweetScienceTypography,
        content     = content
    )
}