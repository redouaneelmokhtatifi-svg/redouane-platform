package com.redouane.educational.platform.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.SavedSearch
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.redouane.educational.platform.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val drawerState = rememberDrawerState(initialValue = androidx.compose.material3.DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var currentScreen by remember { mutableStateOf("home") }
    var isDarkMode by remember { mutableStateOf(false) }

    NavigationDrawer(
        drawerContent = {
            DrawerContent(
                currentScreen = currentScreen,
                onNavigate = { screen ->
                    currentScreen = screen
                    scope.launch { drawerState.close() }
                },
                isDarkMode = isDarkMode,
                onDarkModeToggle = { isDarkMode = !isDarkMode }
            )
        },
        drawerState = drawerState,
        gesturesEnabled = true
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            "منصة ريدوان التعليمية",
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    navigationIcon = {
                        androidx.compose.material.icons.filled.Menu
                        Button(
                            onClick = { scope.launch { drawerState.open() } },
                            modifier = Modifier.padding(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Icon(
                                imageVector = androidx.compose.material.icons.filled.Menu,
                                contentDescription = "Menu",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                )
            }
        ) { paddingValues ->
            when (currentScreen) {
                "home" -> HomeScreen(modifier = Modifier.padding(paddingValues))
                "levels" -> LevelsScreen(modifier = Modifier.padding(paddingValues))
                "favorites" -> FavoritesScreen(modifier = Modifier.padding(paddingValues))
                "saved" -> SavedScreen(modifier = Modifier.padding(paddingValues))
                "settings" -> SettingsScreen(modifier = Modifier.padding(paddingValues))
                "about" -> AboutScreen(modifier = Modifier.padding(paddingValues))
                else -> HomeScreen(modifier = Modifier.padding(paddingValues))
            }
        }
    }
}

@Composable
fun DrawerContent(
    currentScreen: String,
    onNavigate: (String) -> Unit,
    isDarkMode: Boolean,
    onDarkModeToggle: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            "منصة ريدوان",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Divider()

        NavigationDrawerItem(
            label = { Text("الرئيسية") },
            icon = { Icon(Icons.Filled.Home, contentDescription = null) },
            selected = currentScreen == "home",
            onClick = { onNavigate("home") },
            modifier = Modifier.layoutId("home")
        )

        NavigationDrawerItem(
            label = { Text("المستويات") },
            icon = { Icon(Icons.Filled.Book, contentDescription = null) },
            selected = currentScreen == "levels",
            onClick = { onNavigate("levels") }
        )

        NavigationDrawerItem(
            label = { Text("المفضلة") },
            icon = { Icon(Icons.Filled.SavedSearch, contentDescription = null) },
            selected = currentScreen == "favorites",
            onClick = { onNavigate("favorites") }
        )

        NavigationDrawerItem(
            label = { Text("المحفوظات") },
            icon = { Icon(Icons.Filled.Leaderboard, contentDescription = null) },
            selected = currentScreen == "saved",
            onClick = { onNavigate("saved") }
        )

        NavigationDrawerItem(
            label = { Text("الإعدادات") },
            icon = { Icon(Icons.Filled.Settings, contentDescription = null) },
            selected = currentScreen == "settings",
            onClick = { onNavigate("settings") }
        )

        NavigationDrawerItem(
            label = { Text("حول التطبيق") },
            icon = { Icon(Icons.Filled.Info, contentDescription = null) },
            selected = currentScreen == "about",
            onClick = { onNavigate("about") }
        )

        Divider(modifier = Modifier.padding(vertical = 16.dp))

        Button(
            onClick = onDarkModeToggle,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Icon(
                imageVector = if (isDarkMode) Icons.Filled.LightMode else Icons.Filled.DarkMode,
                contentDescription = null,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(if (isDarkMode) "وضع فاتح" else "وضع داكن")
        }
    }
}
