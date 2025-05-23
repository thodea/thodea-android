package com.example.thodea.ui.composables

import android.annotation.SuppressLint
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

/**
 * Composable function that represents the main screen of the application.
 *
 * @param navController The navigation controller used for handling navigation between screens.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        containerColor = Color(0xFF111827), // sets background for entire scaffold
        bottomBar = {
            BottomAppBar(
                containerColor = Color.Transparent, // Make bottom bar transparent
                tonalElevation = 0.dp // Remove elevation shadow if any
            ) { BottomNavigationBar(navController = navController) }
        }
    ) {
        NavigationScreens(navController = navController)
    }
}
