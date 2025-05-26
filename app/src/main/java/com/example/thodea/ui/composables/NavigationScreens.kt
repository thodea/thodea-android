package com.example.thodea.ui.composables

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.thodea.nav.NavItem
import com.example.thodea.ui.composables.tabs.FeedScreen
import com.example.thodea.ui.composables.tabs.PostScreen
import com.example.thodea.ui.composables.tabs.ProfileScreen
import com.example.thodea.ui.composables.tabs.SearchScreen

/**
 * Composable function that defines the navigation screens and their corresponding destinations.
 *
 * @param navController The navigation controller used for handling navigation between screens.
 */
@Composable
fun NavigationScreens(navController: NavHostController) {
    NavHost(navController, startDestination = NavItem.Profile.path) {
        composable(NavItem.Post.path) {
            PostScreen(onNavigateToFeed = {
                navController.navigate(NavItem.Feed.path) {
                    popUpTo(NavItem.Post.path) {
                        inclusive = true
                    }
                }
            })
        }
        composable(NavItem.Feed.path) { FeedScreen() }
        composable(NavItem.Search.path) { SearchScreen() }
        composable(NavItem.Profile.path) { ProfileScreen() }
    }
}