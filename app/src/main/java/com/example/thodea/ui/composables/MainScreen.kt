package com.example.thodea.ui.composables

import android.annotation.SuppressLint
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.thodea.nav.NavItem

/**
 * Composable function that represents the main screen of the application.
 *
 * @param navController The navigation controller used for handling navigation between screens.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val showBottomBar = when (currentRoute) {
        NavItem.Feed.path,
        NavItem.Post.path,
        NavItem.Search.path,
        NavItem.Profile.path -> true
        else -> false
    }

    Scaffold(
        containerColor = Color(0xFF111827), // sets background for entire scaffold
        bottomBar = {
            if (showBottomBar) {
                BottomAppBar(
                    containerColor = Color.Transparent, // Make bottom bar transparent
                    tonalElevation = 0.dp // Remove elevation shadow if any
                ) { BottomNavigationBar(navController = navController) }
            }
        }
    ) {
        NavigationScreens(navController = navController)
    }
}
