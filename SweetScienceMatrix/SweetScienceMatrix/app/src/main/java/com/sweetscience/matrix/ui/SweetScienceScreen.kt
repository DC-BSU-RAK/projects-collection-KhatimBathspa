package com.sweetscience.matrix.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sweetscience.matrix.logic.*
import com.sweetscience.matrix.theme.*
import androidx.compose.material.icons.automirrored.filled.Help

// Note: Removed the duplicate Icons import that was at the bottom

// Main Screen

@Composable
fun SweetScienceScreen(viewModel: CalculatorViewModel) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GymBlack)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
                .padding(top = 52.dp, bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Title bar
            TitleBar(onHelpClick = viewModel::onToggleTrainersManual)

            // Display / input strip
            ComboDisplay(displayText = viewModel.displayText)

            // Result card (shown only after analysis)
            viewModel.result?.let { result ->
                ResultCard(result = result)
            }

            // Calculator grid
            CalculatorGrid(
                onInput   = viewModel::onInput,
                onClear   = viewModel::onClear,
                onAnalyse = viewModel::onAnalyse
            )
        }

        // Trainer's Manual bottom sheet
        if (viewModel.showTrainersManual) {
            TrainersManualSheet(onDismiss = viewModel::onDismissTrainersManual)
        }
    }
}

// Title Bar

@Composable
private fun TitleBar(onHelpClick: () -> Unit) {
    Row(
        modifier            = Modifier.fillMaxWidth(),
        verticalAlignment   = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text         = "SWEET SCIENCE",
                fontSize     = 20.sp,
                fontWeight   = FontWeight.Black,
                color        = TextPrimary,
                letterSpacing = 3.sp
            )
            Text(
                text         = "MATRIX",
                fontSize     = 12.sp,
                fontWeight   = FontWeight.Bold,
                color        = BloodRed,
                letterSpacing = 5.sp
            )
        }
        IconButton(
            onClick  = onHelpClick,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.dp, ChainLink, CircleShape)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Help,
                contentDescription = "Trainer's Manual",
                tint = GoldBell
            )
        }
    }
}

// Combo Display Strip

@Composable
private fun ComboDisplay(displayText: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(RingMat)
            .border(1.dp, ChainLink, RoundedCornerShape(12.dp))
            .padding(horizontal = 16.dp, vertical = 18.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text         = "COMBINATION",
                fontSize     = 9.sp,
                fontWeight   = FontWeight.Bold,
                color        = TextSecondary,
                letterSpacing = 2.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text         = displayText,
                fontSize     = 18.sp,
                fontWeight   = FontWeight.Bold,
                color        = TextPrimary,
                textAlign    = TextAlign.End,
                maxLines     = 2,
                overflow     = TextOverflow.Ellipsis
            )
        }
    }
}

// 4 × 4 Calculator Grid
//
//  Row 1: [  1-Jab  ] [  2-Cross ] [  3-Lead Hk ] [  4-Rear Hk ]
//  Row 2: [  5-Lead↑] [  6-Rear↑ ] [    Slip     ] [    Roll    ]
//  Row 3: [   Pivot ] [  Clinch  ] [      C      ] [   ANALYZE  ]

@Composable
private fun CalculatorGrid(
    onInput: (ComboInput) -> Unit,
    onClear: () -> Unit,
    onAnalyse: () -> Unit
) {
    // Cell height
    val rowH = Modifier.height(70.dp)

    // Shared weight for uniform columns
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        // Row 1: Straight punches
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            PunchButton("1", "JAB",       Modifier.weight(1f).then(rowH)) { onInput(ComboInput.Jab) }
            PunchButton("2", "CROSS",     Modifier.weight(1f).then(rowH)) { onInput(ComboInput.Cross) }
            PunchButton("3", "LEAD HK",   Modifier.weight(1f).then(rowH)) { onInput(ComboInput.LeadHook) }
            PunchButton("4", "REAR HK",   Modifier.weight(1f).then(rowH)) { onInput(ComboInput.RearHook) }
        }

        // Row 2: Uppercuts
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            PunchButton("5", "LEAD UP",   Modifier.weight(1f).then(rowH)) { onInput(ComboInput.LeadUpper) }
            PunchButton("6", "REAR UP",   Modifier.weight(1f).then(rowH)) { onInput(ComboInput.RearUpper) }
            DefenceButton("SLIP",         Modifier.weight(1f).then(rowH)) { onInput(ComboInput.Slip) }
            DefenceButton("ROLL",         Modifier.weight(1f).then(rowH)) { onInput(ComboInput.Roll) }
        }

        // Row 3: Defence + function
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            DefenceButton("PIVOT",        Modifier.weight(1f).then(rowH)) { onInput(ComboInput.Pivot) }
            DefenceButton("CLINCH",       Modifier.weight(1f).then(rowH)) { onInput(ComboInput.Clinch) }
            ClearButton(                  Modifier.weight(1f).then(rowH)) { onClear() }
            AnalyseButton(                Modifier.weight(1f).then(rowH)) { onAnalyse() }
        }

        // Hint label
        Text(
            text         = "Build your combo · max 12 inputs",
            modifier     = Modifier.fillMaxWidth(),
            textAlign    = TextAlign.Center,
            fontSize     = 10.sp,
            color        = TextSecondary,
            letterSpacing = 0.5.sp
        )
    }
}