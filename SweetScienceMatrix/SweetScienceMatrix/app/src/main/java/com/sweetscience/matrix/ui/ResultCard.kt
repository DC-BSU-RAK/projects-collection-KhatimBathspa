package com.sweetscience.matrix.ui

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sweetscience.matrix.logic.Archetype
import com.sweetscience.matrix.logic.ArchetypeResult
import com.sweetscience.matrix.theme.*

// Maps each archetype to its accent colour for the result card border/glow

private fun accentFor(archetype: Archetype): Color = when (archetype) {
    Archetype.OUT_BOXER       -> Emerald
    Archetype.SWARMER         -> Amber
    Archetype.SLUGGER         -> Crimson
    Archetype.COUNTER_PUNCHER -> Violet
    Archetype.UNKNOWN         -> TextSecondary
}

// ResultCard

@Composable
fun ResultCard(result: ArchetypeResult, modifier: Modifier = Modifier) {
    val accent = accentFor(result.archetype)

    AnimatedVisibility(
        visible = true,
        enter   = fadeIn(tween(400)) + slideInVertically(tween(400)) { it / 3 },
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(RingMat)
                .border(width = 1.5.dp, color = accent, shape = RoundedCornerShape(16.dp))
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Archetype icon
            ArchetypeIcon(
                archetype = result.archetype,
                modifier  = Modifier.size(80.dp)
            )

            // Archetype label
            Text(
                text       = result.name.uppercase(),
                style      = MaterialTheme.typography.displayMedium,
                color      = accent,
                fontSize   = 18.sp,
                letterSpacing = 3.sp,
                fontWeight = FontWeight.Black
            )

            // Divider bar
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(2.dp)
                    .background(accent.copy(alpha = 0.5f))
                    .clip(RoundedCornerShape(1.dp))
            )

            // Tactical summary
            Text(
                text  = result.summary,
                style = MaterialTheme.typography.bodyLarge,
                color = TextPrimary.copy(alpha = 0.88f)
            )
        }
    }
}