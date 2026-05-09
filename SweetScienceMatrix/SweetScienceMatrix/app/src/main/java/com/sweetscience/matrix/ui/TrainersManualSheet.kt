package com.sweetscience.matrix.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sweetscience.matrix.theme.*

// Trainer's Manual — Modal Bottom Sheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrainersManualSheet(onDismiss: () -> Unit) {
    ModalBottomSheet(
        onDismissRequest    = onDismiss,
        sheetState          = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        containerColor      = RingMat,
        contentColor        = TextPrimary,
        dragHandle          = {
            // Custom drag handle styled as a chain link
            Box(
                modifier = Modifier
                    .padding(top = 12.dp, bottom = 4.dp)
                    .width(48.dp)
                    .height(4.dp)
                    .clip(RoundedCornerShape(50))
                    .background(ChainLink)
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
                .padding(bottom = 48.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Header
            Text(
                text      = "🥊  TRAINER'S MANUAL",
                style     = MaterialTheme.typography.displayMedium,
                color     = GoldBell,
                modifier  = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Text(
                text      = "The Sweet Science Matrix Number System",
                style     = MaterialTheme.typography.bodyMedium,
                color     = TextSecondary,
                modifier  = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            HorizontalDivider(color = ChainLink)

            // Punch numbering
            SectionHeader("THE 1–6 PUNCH CODE")

            val punches = listOf(
                "1 - Jab" to "A quick straight punch with the lead hand. The setup shot.",
                "2 - Cross" to "A powerful straight punch with the rear hand. The money shot.",
                "3 - Lead Hook" to "A curved punch with the lead hand targeting the temple or body.",
                "4 - Rear Hook" to "A powerful curved punch with the rear hand. Devastating at close range.",
                "5 - Lead Uppercut" to "An upward punch with the lead hand, targeting chin or solar plexus.",
                "6 - Rear Uppercut" to "The most powerful uppercut, fired with the rear hand from close range."
            )
            punches.forEach { (label, desc) ->
                PunchRow(label = label, description = desc)
            }

            HorizontalDivider(color = ChainLink)

            // Defence moves
            SectionHeader("DEFENSIVE MOVES")

            val defences = listOf(
                "Slip"   to "Move the head off the centerline to avoid a straight punch.",
                "Roll"   to "Duck and rotate the torso under a hook, emerging on the outside.",
                "Pivot"  to "Rotate on the lead foot to create a new angle and escape the pocket.",
                "Clinch" to "Tie up the opponent's arms at close range to slow the pace or recover."
            )
            defences.forEach { (label, desc) ->
                PunchRow(label = label, description = desc, accent = IceBlue)
            }

            HorizontalDivider(color = ChainLink)

            // Archetypes
            SectionHeader("THE FOUR ARCHETYPES")

            val archetypes = listOf(
                Triple("🎯  The Out-Boxer",       Emerald,
                    "Dominates with 1s, 2s, and pivots. Wins at long range, controls distance, fights in straight lines."),
                Triple("🌪️  The Swarmer",         Amber,
                    "High hook frequency (3s & 4s) combined with rolls. Applies relentless pressure and works inside."),
                Triple("💥  The Slugger",          Crimson,
                    "Uppercuts (5s & 6s) and clinches. A power-first fighter who thrives at close quarters."),
                Triple("🪤  The Counter-Puncher",  Violet,
                    "Opens with a Slip or Roll, then immediately fires back. A reactive, defensive-first strategist.")
            )
            archetypes.forEach { (name, color, desc) ->
                ArchetypeRow(name = name, color = color, description = desc)
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Dismiss
            Button(
                onClick  = onDismiss,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                colors   = ButtonDefaults.buttonColors(
                    containerColor = BloodRed,
                    contentColor   = TextPrimary
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("GOT IT, COACH", fontWeight = FontWeight.ExtraBold, letterSpacing = 2.sp)
            }
        }
    }
}

// Sub-composables

@Composable
private fun SectionHeader(text: String) {
    Text(
        text  = text,
        style = MaterialTheme.typography.labelLarge,
        color = GoldBell
    )
}

@Composable
private fun PunchRow(label: String, description: String, accent: androidx.compose.ui.graphics.Color = BloodRed) {
    Row(
        modifier            = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .width(4.dp)
                .height(44.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(accent)
        )
        Column {
            Text(text = label, style = MaterialTheme.typography.titleLarge, color = TextPrimary, fontSize = 14.sp)
            Text(text = description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
private fun ArchetypeRow(
    name: String,
    color: androidx.compose.ui.graphics.Color,
    description: String
) {
    Surface(
        color  = CornerSteel,
        shape  = RoundedCornerShape(10.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(text = name, fontWeight = FontWeight.Bold, color = color, fontSize = 15.sp)
            Text(text = description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}