package com.example.lenslightapp

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme // <-- Added this to access our dynamic colors
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Pointing to your actual screens folder
import com.example.lenslightapp.ui.screens.FilmLibraryScreen
import com.example.lenslightapp.ui.screens.LightMeterScreen
import com.example.lenslightapp.ui.screens.LoginScreen
import com.example.lenslightapp.ui.screens.SettingsScreen
import com.example.lenslightapp.ui.screens.ShotLogScreen
import com.example.lenslightapp.ui.screens.SignupScreen

// Pointing to your actual theme folder
import com.example.lenslightapp.ui.theme.LensLightTheme

data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val prefs = getSharedPreferences("lens_light_prefs", Context.MODE_PRIVATE)
        val startLoggedIn = prefs.getBoolean("is_logged_in", false)

        setContent {
            // We removed LensLightTheme from here and moved it inside LensLightApp
            LensLightApp(
                startLoggedIn = startLoggedIn,
                onLoginSuccess = {
                    prefs.edit().putBoolean("is_logged_in", true).apply()
                }
            )
        }
    }
}

@Composable
fun LensLightApp(startLoggedIn: Boolean, onLoginSuccess: () -> Unit) {
    // THIS IS THE BRAIN: It remembers if the theme is dark or light
    var isDarkMode by rememberSaveable { mutableStateOf(true) }

    // The theme wraps the whole app and listens to the `isDarkMode` brain
    LensLightTheme(darkTheme = isDarkMode) {
        var currentScreen by rememberSaveable { mutableStateOf(if (startLoggedIn) "dashboard" else "login") }

        when (currentScreen) {
            "login" -> LoginScreen(onNavigate = {
                if (it == "dashboard") onLoginSuccess()
                currentScreen = it
            })
            "signup" -> SignupScreen(onNavigate = {
                if (it == "dashboard") onLoginSuccess()
                currentScreen = it
            })
            "dashboard" -> DashboardScreen(
                isDarkMode = isDarkMode,
                onThemeChange = { isDarkMode = it } // This allows the switch to change the brain
            )
        }
    }
}

@Composable
fun DashboardScreen(isDarkMode: Boolean, onThemeChange: (Boolean) -> Unit) {
    val navItems = remember {
        listOf(
            BottomNavItem("Meter", Icons.Default.Star, "meter"),
            BottomNavItem("Library", Icons.Default.Favorite, "library"),
            BottomNavItem("Log", Icons.Default.List, "log"),
            BottomNavItem("Settings", Icons.Default.Settings, "settings")
        )
    }
    var selectedRoute by rememberSaveable { mutableStateOf("meter") }

    Scaffold(
        // MAGIC: Instead of Obsidian, it asks the Theme what color to use!
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            NavigationBar(
                // MAGIC: Instead of CardDark, it asks the Theme!
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 0.dp
            ) {
                navItems.forEach { item ->
                    val selected = selectedRoute == item.route
                    NavigationBarItem(
                        selected = selected,
                        onClick = { selectedRoute = item.route },
                        icon = {
                            Icon(item.icon, contentDescription = item.label)
                        },
                        label = {
                            Text(item.label, fontSize = 10.sp)
                        },
                        colors = NavigationBarItemDefaults.colors(
                            // MAGIC: Making the navigation icons adapt to the theme
                            selectedIconColor = MaterialTheme.colorScheme.background,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            unselectedIconColor = MaterialTheme.colorScheme.onBackground,
                            unselectedTextColor = MaterialTheme.colorScheme.onBackground,
                            indicatorColor = MaterialTheme.colorScheme.primary
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (selectedRoute) {
                "meter" -> LightMeterScreen()
                "library" -> FilmLibraryScreen()
                "log" -> ShotLogScreen()
                // Passing the brain down to the Settings Screen
                "settings" -> SettingsScreen(isDarkMode = isDarkMode, onThemeChange = onThemeChange)
            }
        }
    }
}