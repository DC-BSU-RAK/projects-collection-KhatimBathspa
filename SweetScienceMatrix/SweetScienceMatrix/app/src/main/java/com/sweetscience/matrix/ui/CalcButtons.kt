package com.sweetscience.matrix.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sweetscience.matrix.theme.*

// ─────────────────────────────────────────────────────────────────────────────
// Generic Calculator Button
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun CalcButton(
    label: String,
    modifier: Modifier = Modifier,
    baseColor: Color   = CornerSteel,
    pressColor: Color  = baseColor.copy(alpha = 0.6f),
    textColor: Color   = TextPrimary,
    borderColor: Color = ChainLink,
    fontSize: Float    = 15f,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val bg by animateColorAsState(
        targetValue = if (isPressed) pressColor else baseColor,
        animationSpec = tween(80),
        label = "buttonBg"
    )
    val scale = if (isPressed) 0.94f else 1f

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .scale(scale)
            .clip(RoundedCornerShape(10.dp))
            .background(bg)
            .border(1.dp, borderColor, RoundedCornerShape(10.dp))
            .clickable(
                interactionSource = interactionSource,
                indication        = rememberRipple(color = textColor.copy(alpha = 0.2f)),
                onClick           = onClick
            )
            .padding(vertical = 12.dp, horizontal = 4.dp)
    ) {
        Text(
            text      = label,
            color     = textColor,
            fontSize  = fontSize.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            maxLines  = 1
        )
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Punch Button (numbered: 1–6)
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun PunchButton(
    number: String,
    sublabel: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale = if (isPressed) 0.93f else 1f
    val bg by animateColorAsState(
        if (isPressed) BloodRed.copy(alpha = 0.55f) else CornerSteel,
        tween(80), "punchBg"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .scale(scale)
            .clip(RoundedCornerShape(10.dp))
            .background(bg)
            .border(1.dp, if (isPressed) BloodRed else ChainLink, RoundedCornerShape(10.dp))
            .clickable(
                interactionSource = interactionSource,
                indication        = rememberRipple(color = BloodRed.copy(alpha = 0.3f)),
                onClick           = onClick
            )
            .padding(vertical = 10.dp)
    ) {
        Text(text = number, color = BloodRed, fontSize = 22.sp, fontWeight = FontWeight.Black)
        Text(text = sublabel, color = TextSecondary, fontSize = 9.sp, fontWeight = FontWeight.SemiBold, letterSpacing = 0.5.sp)
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Defence Button (Slip / Roll / Pivot / Clinch)
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun DefenceButton(
    label: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    CalcButton(
        label       = label,
        modifier    = modifier,
        baseColor   = Color(0xFF0D1B2A),
        pressColor  = IceBlue.copy(alpha = 0.25f),
        textColor   = IceBlue,
        borderColor = IceBlue.copy(alpha = 0.35f),
        fontSize    = 13f,
        onClick     = onClick
    )
}

// ─────────────────────────────────────────────────────────────────────────────
// Clear Button
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun ClearButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    CalcButton(
        label       = "C",
        modifier    = modifier,
        baseColor   = Color(0xFF1A0A0A),
        pressColor  = BloodRed.copy(alpha = 0.30f),
        textColor   = BloodRed,
        borderColor = BloodRed.copy(alpha = 0.35f),
        fontSize    = 18f,
        onClick     = onClick
    )
}

// ─────────────────────────────────────────────────────────────────────────────
// Analyse Button (the "equals" / CTA)
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun AnalyseButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale = if (isPressed) 0.96f else 1f
    val bg by animateColorAsState(
        if (isPressed) GoldBell.copy(alpha = 0.75f) else GoldBell,
        tween(80), "analyseBg"
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .scale(scale)
            .clip(RoundedCornerShape(10.dp))
            .background(bg)
            .clickable(
                interactionSource = interactionSource,
                indication        = rememberRipple(color = GymBlack.copy(alpha = 0.2f)),
                onClick           = onClick
            )
            .padding(vertical = 12.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text       = "ANALYZE",
                color      = GymBlack,
                fontSize   = 11.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 2.sp
            )
            Text(
                text       = "COMBO",
                color      = GymBlack,
                fontSize   = 11.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 2.sp
            )
        }
    }
}
