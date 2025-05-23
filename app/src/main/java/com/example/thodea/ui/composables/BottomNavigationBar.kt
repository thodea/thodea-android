package com.example.thodea.ui.composables

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.thodea.nav.NavItem

/**
 * Composable function that represents the bottom navigation bar of the application.
 *
 * @param navController The navigation controller used for handling navigation between screens.
 */
@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val navItems = listOf(NavItem.Post, NavItem.Feed, NavItem.Search, NavItem.Profile)

    // Observe the current back stack entry
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        containerColor = Color(0x99172554), // 0x99 = 60% opacity (in hex) of rgb(23,37,84)
        tonalElevation = 0.dp
    ) {
        navItems.forEach { item ->
            NavigationBarItem(
                icon = { Icon(painter = painterResource(id = item.icon), contentDescription = item.title) },
                label = null, // hides label
                alwaysShowLabel = false,
                selected = currentRoute == item.path, // check against current route
                onClick = {
                    if (currentRoute != item.path) {
                        navController.navigate(item.path) {
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) { saveState = true }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color(0xFF1E3A8A), // rgb(30,58,138)
                    selectedIconColor = Color(0xFF6B7280),
                    unselectedIconColor = Color(0xFF6B7280)
                )
            )
        }
    }
}