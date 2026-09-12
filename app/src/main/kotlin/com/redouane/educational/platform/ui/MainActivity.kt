package com.redouane.educational.platform.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.redouane.educational.platform.R
import com.redouane.educational.platform.ui.screens.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isDarkTheme = isSystemInDarkTheme()
            
            val colorScheme = if (isDarkTheme) {
                darkColorScheme(
                    primary = Color(0xFF6D28D9),
                    secondary = Color(0xFF8B5CF6),
                    tertiary = Color(0xFF10B981),
                    background = Color(0xFF1F2937),
                    surface = Color(0xFF111827),
                    onPrimary = Color.White,
                    onSecondary = Color.White,
                    onTertiary = Color.White,
                    onBackground = Color(0xFFE5E7EB),
                    onSurface = Color(0xFFE5E7EB)
                )
            } else {
                lightColorScheme(
                    primary = Color(0xFF7C3AED),
                    secondary = Color(0xFFA78BFA),
                    tertiary = Color(0xFF10B981),
                    background = Color(0xFFFAFAFA),
                    surface = Color.White,
                    onPrimary = Color.White,
                    onSecondary = Color.White,
                    onTertiary = Color.White,
                    onBackground = Color(0xFF1F2937),
                    onSurface = Color(0xFF1F2937)
                )
            }
            
            MaterialTheme(
                colorScheme = colorScheme,
                typography = androidx.compose.material3.Typography()
            ) {
                MainScreen()
            }
        }
    }
}
