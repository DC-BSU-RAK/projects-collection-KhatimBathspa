package com.example.lenslightapp.ui.screens

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lenslightapp.ui.theme.AmberGold
import com.example.lenslightapp.ui.theme.Obsidian
import com.example.lenslightapp.ui.theme.SubtleGray
import java.util.Locale
import kotlin.math.roundToInt

// Auth Screens

@Composable
fun LoginScreen(onNavigate: (String) -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(CircleShape)
                    .background(AmberGold),
                contentAlignment = Alignment.Center
            ) {
                Text("L", fontSize = 32.sp, fontWeight = FontWeight.Black, color = Obsidian)
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                "Lens \$ Light",
                fontSize = 28.sp,
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colorScheme.onBackground,
                letterSpacing = 1.5.sp
            )
            Text(
                "YOUR PHOTOGRAPHER'S TOOLKIT",
                fontSize = 10.sp,
                color = AmberGold,
                letterSpacing = 3.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
            Spacer(modifier = Modifier.height(40.dp))
            AuthTextField(value = email, onValueChange = { email = it }, label = "Email")
            Spacer(modifier = Modifier.height(12.dp))
            AuthTextField(
                value = password,
                onValueChange = { password = it },
                label = "Password",
                isPassword = true
            )
            Spacer(modifier = Modifier.height(28.dp))
            Button(
                onClick = { onNavigate("dashboard") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = AmberGold)
            ) {
                Text("Sign In", fontWeight = FontWeight.Bold, color = Obsidian, fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))
            TextButton(onClick = { onNavigate("signup") }) {
                Text("Don't have an account? ", color = SubtleGray, fontSize = 14.sp)
                Text("Sign Up", color = AmberGold, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }
        }
    }
}

@Composable
fun SignupScreen(onNavigate: (String) -> Unit) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { onNavigate("login") }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = MaterialTheme.colorScheme.onBackground)
                }
                Text(
                    "Create Account",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            AuthTextField(value = name, onValueChange = { name = it }, label = "Full Name")
            Spacer(modifier = Modifier.height(12.dp))
            AuthTextField(value = email, onValueChange = { email = it }, label = "Email")
            Spacer(modifier = Modifier.height(12.dp))
            AuthTextField(
                value = password,
                onValueChange = { password = it },
                label = "Password",
                isPassword = true
            )
            Spacer(modifier = Modifier.height(28.dp))
            Button(
                onClick = { onNavigate("dashboard") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = AmberGold)
            ) {
                Text("Create Account", fontWeight = FontWeight.Bold, color = Obsidian, fontSize = 16.sp)
            }
        }
    }
}

@Composable
private fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isPassword: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, color = SubtleGray) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        shape = RoundedCornerShape(10.dp),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else androidx.compose.ui.text.input.VisualTransformation.None,
        leadingIcon = {
            Icon(
                if (isPassword) Icons.Default.Lock else Icons.Default.Person,
                contentDescription = null,
                tint = AmberGold
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = AmberGold,
            unfocusedBorderColor = SubtleGray,
            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
            cursorColor = AmberGold,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface
        )
    )
}

// Light Meter Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LightMeterScreen() {
    var isoIndex by remember { mutableStateOf(2f) }
    var apertureIndex by remember { mutableStateOf(2f) }
    var shutterIndex by remember { mutableStateOf(4f) }
    var showSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    val isoValues = listOf("100", "200", "400", "800", "1600", "3200")
    val apertureValues = listOf("f/1.4", "f/2", "f/2.8", "f/4", "f/5.6", "f/8", "f/11")
    val shutterValues = listOf("1/2000", "1/1000", "1/500", "1/250", "1/125", "1/60", "1/30", "1/15")

    val ev = calculateEV(isoIndex.roundToInt(), apertureIndex.roundToInt(), shutterIndex.roundToInt())
    val exposureLabel = when {
        ev < -1 -> "UNDEREXPOSED"
        ev > 1 -> "OVEREXPOSED"
        else -> "BALANCED"
    }
    val exposureColor = when {
        ev < -1 -> Color(0xFF5C9BFF)
        ev > 1 -> Color(0xFFFF6B6B)
        else -> AmberGold
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("Light Meter", fontSize = 24.sp, fontWeight = FontWeight.Black, color = MaterialTheme.colorScheme.onBackground)
                Text("Exposure Triangle", fontSize = 12.sp, color = SubtleGray, letterSpacing = 2.sp)
            }
            IconButton(
                onClick = { showSheet = true },
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                Icon(Icons.Default.Info, contentDescription = "Info", tint = AmberGold)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        ExposureGauge(ev = ev, exposureLabel = exposureLabel, exposureColor = exposureColor)

        Spacer(modifier = Modifier.height(28.dp))

        ExposureSlider(
            label = "ISO",
            tag = "SENSITIVITY",
            index = isoIndex,
            maxIndex = (isoValues.size - 1).toFloat(),
            displayValue = "ISO ${isoValues[isoIndex.roundToInt()]}",
            onValueChange = { isoIndex = it }
        )
        Spacer(modifier = Modifier.height(20.dp))
        ExposureSlider(
            label = "Aperture",
            tag = "DEPTH OF FIELD",
            index = apertureIndex,
            maxIndex = (apertureValues.size - 1).toFloat(),
            displayValue = apertureValues[apertureIndex.roundToInt()],
            onValueChange = { apertureIndex = it }
        )
        Spacer(modifier = Modifier.height(20.dp))
        ExposureSlider(
            label = "Shutter Speed",
            tag = "MOTION BLUR",
            index = shutterIndex,
            maxIndex = (shutterValues.size - 1).toFloat(),
            displayValue = shutterValues[shutterIndex.roundToInt()] + "s",
            onValueChange = { shutterIndex = it }
        )
    }

    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false },
            sheetState = sheetState,
            containerColor = MaterialTheme.colorScheme.surface
        ) {
            ExposureTriangleGuide()
        }
    }
}

private fun calculateEV(isoIdx: Int, apIdx: Int, shutterIdx: Int): Float {
    val isoScore = isoIdx * 0.5f
    val apScore = -(apIdx * 0.4f)
    val shutterScore = -(shutterIdx * 0.3f)
    return isoScore + apScore + shutterScore
}

@Composable
private fun ExposureGauge(ev: Float, exposureLabel: String, exposureColor: Color) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(exposureLabel, fontSize = 11.sp, color = exposureColor, letterSpacing = 3.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "EV ${if (ev >= 0) "+" else ""}${String.format(Locale.US, "%.1f", ev)}",
                fontSize = 42.sp,
                fontWeight = FontWeight.Black,
                color = exposureColor
            )
            Spacer(modifier = Modifier.height(12.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(50))
                    .background(Color(0xFF333333))
            ) {
                val fraction = ((ev + 3f) / 6f).coerceIn(0f, 1f)
                Box(
                    modifier = Modifier
                        .fillMaxWidth(fraction)
                        .height(6.dp)
                        .background(exposureColor)
                )
            }
        }
    }
}

@Composable
private fun ExposureSlider(
    label: String,
    tag: String,
    index: Float,
    maxIndex: Float,
    displayValue: String,
    onValueChange: (Float) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(label, fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.onBackground)
                Text(tag, fontSize = 10.sp, color = SubtleGray, letterSpacing = 2.sp)
            }
            Text(
                displayValue,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = AmberGold
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Slider(
            value = index,
            onValueChange = onValueChange,
            valueRange = 0f..maxIndex,
            steps = (maxIndex - 1).roundToInt(),
            colors = SliderDefaults.colors(
                thumbColor = AmberGold,
                activeTrackColor = AmberGold,
                inactiveTrackColor = Color(0xFF444444)
            )
        )
    }
}

@Composable
private fun ExposureTriangleGuide() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp)
            .padding(bottom = 32.dp)
    ) {
        Text(
            "The Exposure Triangle",
            fontSize = 20.sp,
            fontWeight = FontWeight.Black,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            "Three pillars of a perfect shot",
            fontSize = 12.sp,
            color = AmberGold,
            letterSpacing = 1.sp,
            modifier = Modifier.padding(top = 2.dp, bottom = 20.dp)
        )
        GuideItem(
            title = "ISO — Sensitivity",
            body = "Controls the sensor's sensitivity to light. Low ISO (100–400) gives clean, sharp images. High ISO (1600+) introduces grain but works in dark environments."
        )
        GuideItem(
            title = "Aperture — Lens Opening",
            body = "Written as f/stop. A wide aperture (f/1.4) lets in more light and creates a shallow depth of field — ideal for portraits. A narrow aperture (f/11) keeps everything sharp."
        )
        GuideItem(
            title = "Shutter Speed — Exposure Time",
            body = "How long the sensor is exposed to light. Fast speeds (1/1000s) freeze motion. Slow speeds (1/30s or longer) create motion blur and require a tripod."
        )
        HorizontalDivider(color = Color(0xFF333333), modifier = Modifier.padding(vertical = 16.dp))
        Text(
            "Balance all three to achieve your target EV (Exposure Value). Changing one requires compensating with another to maintain consistent exposure.",
            fontSize = 13.sp,
            color = SubtleGray,
            lineHeight = 20.sp
        )
    }
}

@Composable
private fun GuideItem(title: String, body: String) {
    Column(modifier = Modifier.padding(bottom = 16.dp)) {
        Text(title, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = AmberGold)
        Spacer(modifier = Modifier.height(4.dp))
        Text(body, fontSize = 13.sp, color = SubtleGray, lineHeight = 20.sp)
    }
}

// Film Library Screen

data class FilmStock(
    val name: String,
    val brand: String,
    val iso: Int,
    val type: String,
    val character: String,
    val accentColor: Color
)

@Composable
fun FilmLibraryScreen() {
    val films = remember {
        listOf(
            FilmStock("Portra 400", "Kodak", 400, "Color Negative", "Warm skin tones, fine grain, exceptional latitude.", Color(0xFFFFA726)),
            FilmStock("Ektar 100", "Kodak", 100, "Color Negative", "Ultra-fine grain, vivid saturation, ideal for landscapes.", Color(0xFF42A5F5)),
            FilmStock("Tri-X 400", "Kodak", 400, "B&W Negative", "Classic grain, high contrast, the soul of street photography.", Color(0xFF9E9E9E)),
            FilmStock("HP5 Plus", "Ilford", 400, "B&W Negative", "Versatile, pushes beautifully, reliable in all conditions.", Color(0xFFBDBDBD)),
            FilmStock("Velvia 50", "Fujifilm", 50, "Color Reversal", "Hyper-saturated colors, extreme sharpness, legendary contrast.", Color(0xFF66BB6A)),
            FilmStock("Provia 100F", "Fujifilm", 100, "Color Reversal", "Neutral, fine grain, accurate colors. The professional standard.", Color(0xFFAB47BC)),
            FilmStock("Neopan 100", "Fujifilm", 100, "B&W Negative", "Smooth tones, clean shadows, refined midtone quality.", Color(0xFF78909C)),
            FilmStock("Gold 200", "Kodak", 200, "Color Negative", "Warm, golden cast. Budget-friendly with charming character.", Color(0xFFFFD54F))
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 8.dp)) {
            Text("Film Library", fontSize = 24.sp, fontWeight = FontWeight.Black, color = MaterialTheme.colorScheme.onBackground)
            Text("Classic stocks", fontSize = 12.sp, color = SubtleGray, letterSpacing = 2.sp)
        }
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(films) { film ->
                FilmCard(film)
            }
        }
    }
}

@Composable
private fun FilmCard(film: FilmStock) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(film.accentColor.copy(alpha = 0.15f))
                    .border(1.dp, film.accentColor.copy(alpha = 0.4f), RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    film.iso.toString(),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Black,
                    color = film.accentColor
                )
            }
            Spacer(modifier = Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(film.name, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground)
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(film.accentColor.copy(alpha = 0.2f))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(film.type, fontSize = 9.sp, color = film.accentColor, letterSpacing = 1.sp, fontWeight = FontWeight.Bold)
                    }
                }
                Text(film.brand, fontSize = 12.sp, color = SubtleGray, modifier = Modifier.padding(top = 1.dp))
                Text(
                    film.character,
                    fontSize = 12.sp,
                    color = SubtleGray,
                    lineHeight = 17.sp,
                    modifier = Modifier.padding(top = 5.dp)
                )
            }
        }
    }
}

// Shot Log Screen

data class ShotLogEntry(
    val id: Long,
    val title: String,
    val iso: String,
    val aperture: String,
    val shutter: String,
    val notes: String
)

@Composable
fun ShotLogScreen() {
    val entries = remember { mutableStateListOf<ShotLogEntry>() }
    var showForm by remember { mutableStateOf(false) }
    var titleInput by remember { mutableStateOf("") }
    var isoInput by remember { mutableStateOf("") }
    var apertureInput by remember { mutableStateOf("") }
    var shutterInput by remember { mutableStateOf("") }
    var notesInput by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 12.dp, top = 20.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("Shot Log", fontSize = 24.sp, fontWeight = FontWeight.Black, color = MaterialTheme.colorScheme.onBackground)
                Text("${entries.size} logged shots", fontSize = 12.sp, color = SubtleGray, letterSpacing = 2.sp)
            }
            IconButton(
                onClick = { showForm = !showForm },
                modifier = Modifier
                    .size(42.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(AmberGold)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Shot", tint = Obsidian)
            }
        }

        AnimatedVisibility(
            visible = showForm,
            enter = fadeIn(tween(250)) + slideInVertically(tween(250))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(16.dp)
            ) {
                Text("New Shot", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground)
                Spacer(modifier = Modifier.height(12.dp))
                LogTextField(value = titleInput, onValueChange = { titleInput = it }, label = "Shot Title")
                Spacer(modifier = Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    LogTextField(value = isoInput, onValueChange = { isoInput = it }, label = "ISO", modifier = Modifier.weight(1f))
                    LogTextField(value = apertureInput, onValueChange = { apertureInput = it }, label = "Aperture", modifier = Modifier.weight(1f))
                    LogTextField(value = shutterInput, onValueChange = { shutterInput = it }, label = "Shutter", modifier = Modifier.weight(1f))
                }
                Spacer(modifier = Modifier.height(8.dp))
                LogTextField(value = notesInput, onValueChange = { notesInput = it }, label = "Notes (optional)")
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = {
                        if (titleInput.isNotBlank()) {
                            entries.add(0,
                                ShotLogEntry(
                                    id = System.currentTimeMillis(),
                                    title = titleInput,
                                    iso = isoInput.ifBlank { "—" },
                                    aperture = apertureInput.ifBlank { "—" },
                                    shutter = shutterInput.ifBlank { "—" },
                                    notes = notesInput
                                )
                            )
                            titleInput = ""; isoInput = ""; apertureInput = ""; shutterInput = ""; notesInput = ""
                            showForm = false
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = AmberGold)
                ) {
                    Icon(Icons.Default.Check, contentDescription = null, tint = Obsidian)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Save Shot", fontWeight = FontWeight.Bold, color = Obsidian)
                }
            }
        }

        if (entries.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("No shots logged yet.", color = SubtleGray, fontSize = 16.sp)
                    Text("Tap + to record your first shot.", color = SubtleGray, fontSize = 13.sp, modifier = Modifier.padding(top = 4.dp))
                }
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(entries, key = { it.id }) { entry ->
                    ShotCard(entry = entry, onDelete = { entries.remove(entry) })
                }
            }
        }
    }
}

@Composable
private fun LogTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, fontSize = 12.sp, color = SubtleGray) },
        modifier = modifier,
        singleLine = true,
        shape = RoundedCornerShape(8.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = AmberGold,
            unfocusedBorderColor = SubtleGray,
            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
            cursorColor = AmberGold,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface
        )
    )
}

@Composable
private fun ShotCard(entry: ShotLogEntry, onDelete: () -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(entry.title, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground)
                IconButton(
                    onClick = onDelete,
                    modifier = Modifier.size(28.dp)
                ) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete", tint = SubtleGray, modifier = Modifier.size(18.dp))
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                MetaChip("ISO ${entry.iso}")
                MetaChip(entry.aperture)
                MetaChip(entry.shutter)
            }
            if (entry.notes.isNotBlank()) {
                Text(
                    entry.notes,
                    fontSize = 12.sp,
                    color = SubtleGray,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

@Composable
private fun MetaChip(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(text, fontSize = 11.sp, color = AmberGold, fontWeight = FontWeight.Bold)
    }
}

// Settings Screen

@Composable
fun SettingsScreen(isDarkMode: Boolean, onThemeChange: (Boolean) -> Unit) {
    val context = LocalContext.current
    val prefs = remember { context.getSharedPreferences("lens_light_prefs", Context.MODE_PRIVATE) }
    var rawFormat by remember { mutableStateOf(prefs.getBoolean("raw_format", false)) }
    var notifications by remember { mutableStateOf(prefs.getBoolean("notifications", true)) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Text("Settings", fontSize = 24.sp, fontWeight = FontWeight.Black, color = MaterialTheme.colorScheme.onBackground)
        Text("Preferences", fontSize = 12.sp, color = SubtleGray, letterSpacing = 2.sp)

        Spacer(modifier = Modifier.height(24.dp))

        SettingsSectionLabel("DISPLAY")

        SettingsToggle(
            label = "Dark Mode",
            subtitle = "Use dark theme throughout the app",
            checked = isDarkMode,
            onCheckedChange = { isChecked ->
                onThemeChange(isChecked)
                prefs.edit().putBoolean("dark_mode", isChecked).apply()
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        SettingsSectionLabel("CAPTURE FORMAT")
        SettingsToggle(
            label = "Prefer RAW Format",
            subtitle = "Log shots with RAW as default format",
            checked = rawFormat,
            onCheckedChange = {
                rawFormat = it
                prefs.edit().putBoolean("raw_format", it).apply()
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        SettingsSectionLabel("NOTIFICATIONS")
        SettingsToggle(
            label = "Enable Notifications",
            subtitle = "Reminders and shooting tips",
            checked = notifications,
            onCheckedChange = {
                notifications = it
                prefs.edit().putBoolean("notifications", it).apply()
            }
        )

        Spacer(modifier = Modifier.height(32.dp))
        SettingsSectionLabel("ABOUT")
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(AmberGold),
                    contentAlignment = Alignment.Center
                ) {
                    Text("L", fontSize = 26.sp, fontWeight = FontWeight.Black, color = Obsidian)
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text("Lens \$ Light", fontSize = 18.sp, fontWeight = FontWeight.Black, color = MaterialTheme.colorScheme.onBackground)
                Text("Version 1.0.0", fontSize = 12.sp, color = SubtleGray, modifier = Modifier.padding(top = 2.dp))
                HorizontalDivider(
                    color = SubtleGray,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
                Text("Developed by", fontSize = 12.sp, color = SubtleGray, letterSpacing = 1.sp)
                Text(
                    "Khatim Muhammad",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    color = AmberGold,
                    letterSpacing = 1.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Text(
                    "Lens \$ Light Project",
                    fontSize = 11.sp,
                    color = SubtleGray,
                    letterSpacing = 2.sp,
                    modifier = Modifier.padding(top = 2.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Built with Jetpack Compose",
                    fontSize = 11.sp,
                    color = SubtleGray,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }
}

@Composable
private fun SettingsSectionLabel(label: String) {
    Text(
        label,
        fontSize = 10.sp,
        color = AmberGold,
        letterSpacing = 2.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
private fun SettingsToggle(
    label: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surface)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onCheckedChange(!checked) }
            .padding(horizontal = 16.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(label, fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.onBackground)
            Text(subtitle, fontSize = 12.sp, color = SubtleGray, modifier = Modifier.padding(top = 2.dp))
        }
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Obsidian,
                checkedTrackColor = AmberGold,
                uncheckedThumbColor = MaterialTheme.colorScheme.onBackground,
                uncheckedTrackColor = SubtleGray
            )
        )
    }
}