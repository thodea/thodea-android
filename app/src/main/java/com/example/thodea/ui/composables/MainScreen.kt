package com.example.thodea.ui.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.thodea.AuthClient
import com.example.thodea.nav.NavItem

/***
 * Composable function that represents the main screen of the application.
 * It sets up the overall layout with a Scaffold, handles the visibility of the
 * bottom navigation bar, and hosts the navigation graph.
 *
 * @param navController The navigation controller used for handling navigation between screens.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController, isSignedIn: Boolean, googleAuthClient: AuthClient) {
    // Observe the current back stack entry to determine the current route
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Determine if the bottom navigation bar should be shown based on the current route
    val showBottomBar = when (currentRoute) {
        NavItem.Feed.path,
        NavItem.Post.path,
        NavItem.Search.path,
        NavItem.Profile.path -> true
        else -> false
    }

    // Scaffold provides the basic visual structure for Material Design layouts
    Scaffold(
        containerColor = Color(0xFF111827), // Sets background color for the entire scaffold
        bottomBar = {
            // Only show the BottomAppBar if showBottomBar is true
            if (showBottomBar) {
                BottomAppBar(
                    containerColor = Color.Transparent, // Make bottom bar transparent
                    tonalElevation = 0.dp // Remove elevation shadow if any
                ) {
                    // Your custom BottomNavigationBar composable
                    BottomNavigationBar(navController = navController)
                }
            }
        }
    ) { paddingValues -> // paddingValues account for the top and bottom bars
        // This is where the actual navigation graph is defined and rendered
        // The paddingValues are passed to ensure content doesn't go under the bottom bar
        NavigationScreens(
            navController = navController,
            isLoggedIn = isSignedIn, // 👈 For testing, force the Login screen
            googleAuthClient = googleAuthClient,
            modifier = Modifier.padding(paddingValues)
        )
    }
}
