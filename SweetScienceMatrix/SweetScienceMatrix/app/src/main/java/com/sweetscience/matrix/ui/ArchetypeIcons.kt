package com.sweetscience.matrix.ui

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import com.sweetscience.matrix.logic.Archetype
import com.sweetscience.matrix.theme.*

/**
 * Dispatches to the correct archetype icon based on the [archetype] value.
 */
@Composable
fun ArchetypeIcon(archetype: Archetype, modifier: Modifier = Modifier) {
    when (archetype) {
        Archetype.OUT_BOXER       -> OutBoxerIcon(modifier)
        Archetype.SWARMER         -> SwarmerIcon(modifier)
        Archetype.SLUGGER         -> SluggerIcon(modifier)
        Archetype.COUNTER_PUNCHER -> CounterPuncherIcon(modifier)
        Archetype.UNKNOWN         -> UnknownIcon(modifier)
    }
}

// ─── Out-Boxer: a target/crosshair — precision and distance ──────────────────
@Composable
fun OutBoxerIcon(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val cx = size.width / 2f
        val cy = size.height / 2f
        val r  = size.minDimension / 2f * 0.85f

        drawCircle(color = Emerald, radius = r, center = Offset(cx, cy), style = Stroke(width = size.minDimension * 0.06f))
        drawCircle(color = Emerald, radius = r * 0.55f, center = Offset(cx, cy), style = Stroke(width = size.minDimension * 0.04f))
        drawCircle(color = Emerald.copy(alpha = 0.6f), radius = r * 0.15f, center = Offset(cx, cy))
        // crosshair lines
        val arm = r * 0.35f
        listOf(
            Offset(cx - r * 1.05f, cy) to Offset(cx - arm, cy),
            Offset(cx + arm, cy)       to Offset(cx + r * 1.05f, cy),
            Offset(cx, cy - r * 1.05f) to Offset(cx, cy - arm),
            Offset(cx, cy + arm)       to Offset(cx, cy + r * 1.05f),
        ).forEach { (s, e) ->
            drawLine(Emerald, s, e, strokeWidth = size.minDimension * 0.04f, cap = StrokeCap.Round)
        }
    }
}

// ─── Swarmer: a swarm of radiating fists / impact burst ──────────────────────
@Composable
fun SwarmerIcon(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val cx   = size.width / 2f
        val cy   = size.height / 2f
        val r    = size.minDimension / 2f
        val sw   = size.minDimension * 0.05f
        val rays = 8

        for (i in 0 until rays) {
            val angle = Math.toRadians(i * (360.0 / rays)).toFloat()
            val inner = r * 0.30f
            val outer = r * 0.85f + (if (i % 2 == 0) 0f else -r * 0.18f)
            drawLine(
                color       = Amber,
                start       = Offset(cx + inner * kotlin.math.cos(angle), cy + inner * kotlin.math.sin(angle)),
                end         = Offset(cx + outer * kotlin.math.cos(angle), cy + outer * kotlin.math.sin(angle)),
                strokeWidth = sw,
                cap         = StrokeCap.Round
            )
        }
        // Central fist (simplified circle with knuckle dashes)
        drawCircle(Amber, radius = r * 0.28f, center = Offset(cx, cy))
        drawCircle(Color.Black.copy(alpha = 0.45f), radius = r * 0.28f, center = Offset(cx, cy), style = Stroke(sw * 0.5f))
    }
}

// ─── Slugger: a heavy power fist / diamond shape ─────────────────────────────
@Composable
fun SluggerIcon(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val cx = size.width / 2f
        val cy = size.height / 2f
        val r  = size.minDimension / 2f * 0.82f
        val sw = size.minDimension * 0.055f

        // Diamond outline
        val diamond = Path().apply {
            moveTo(cx, cy - r)
            lineTo(cx + r * 0.70f, cy)
            lineTo(cx, cy + r)
            lineTo(cx - r * 0.70f, cy)
            close()
        }
        drawPath(diamond, color = Crimson.copy(alpha = 0.20f))
        drawPath(diamond, color = Crimson, style = Stroke(width = sw))

        // Inner bold cross
        drawLine(Crimson, Offset(cx, cy - r * 0.45f), Offset(cx, cy + r * 0.45f), sw * 1.4f, StrokeCap.Round)
        drawLine(Crimson, Offset(cx - r * 0.45f, cy), Offset(cx + r * 0.45f, cy), sw * 1.4f, StrokeCap.Round)
    }
}

// ─── Counter-Puncher: yin-yang duality / serpent curve ───────────────────────
@Composable
fun CounterPuncherIcon(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val cx = size.width / 2f
        val cy = size.height / 2f
        val r  = size.minDimension / 2f * 0.82f
        val sw = size.minDimension * 0.05f

        // Outer circle
        drawCircle(color = Violet, radius = r, center = Offset(cx, cy), style = Stroke(sw))

        // Upper half filled
        drawArc(
            color      = Violet.copy(alpha = 0.35f),
            startAngle = 180f,
            sweepAngle = 180f,
            useCenter  = true,
            topLeft    = Offset(cx - r, cy - r),
            size       = Size(r * 2, r * 2)
        )
        // Small circle top (white dot inside filled half)
        drawCircle(Violet, radius = r * 0.18f, center = Offset(cx, cy - r * 0.5f))
        // Small void circle bottom
        drawCircle(Color.Black, radius = r * 0.18f, center = Offset(cx, cy + r * 0.5f))

        // Dividing S-curve (approximated with two arcs)
        drawArc(
            color      = Violet,
            startAngle = 270f,
            sweepAngle = 180f,
            useCenter  = false,
            topLeft    = Offset(cx - r * 0.5f, cy - r),
            size       = Size(r, r),
            style      = Stroke(sw)
        )
        drawArc(
            color      = Violet,
            startAngle = 90f,
            sweepAngle = 180f,
            useCenter  = false,
            topLeft    = Offset(cx - r * 0.5f, cy),
            size       = Size(r, r),
            style      = Stroke(sw)
        )
    }
}

// ─── Unknown: a question-mark silhouette ─────────────────────────────────────
@Composable
fun UnknownIcon(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val cx = size.width / 2f
        val cy = size.height / 2f
        val r  = size.minDimension / 2f * 0.82f
        val sw = size.minDimension * 0.07f

        drawCircle(TextSecondary.copy(alpha = 0.2f), r)
        drawCircle(TextSecondary, r, style = Stroke(sw * 0.6f))
        // Dot
        drawCircle(TextSecondary, r * 0.1f, center = Offset(cx, cy + r * 0.42f))
        // Question arc
        drawArc(
            color      = TextSecondary,
            startAngle = 200f,
            sweepAngle = 200f,
            useCenter  = false,
            topLeft    = Offset(cx - r * 0.38f, cy - r * 0.55f),
            size       = Size(r * 0.76f, r * 0.60f),
            style      = Stroke(sw, cap = StrokeCap.Round)
        )
        // Vertical stem of ?
        drawLine(TextSecondary, Offset(cx, cy - r * 0.04f), Offset(cx, cy + r * 0.24f), sw, StrokeCap.Round)
    }
}
