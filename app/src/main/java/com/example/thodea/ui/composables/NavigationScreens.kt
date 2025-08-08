package com.example.thodea.ui.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.thodea.AuthClient
import com.example.thodea.nav.NavItem
import com.example.thodea.ui.composables.tabs.FeedScreen
import com.example.thodea.ui.composables.tabs.PostScreen
import com.example.thodea.ui.composables.tabs.ProfileScreen
import com.example.thodea.ui.composables.tabs.SearchScreen
import com.example.thodea.ui.composables.tabs.profile.ChatRequestsScreen
import com.example.thodea.ui.composables.tabs.profile.ChatScreen
import com.example.thodea.ui.composables.tabs.profile.ChatsScreen
import com.example.thodea.ui.composables.tabs.profile.FollowInfoScreen
import com.example.thodea.ui.composables.tabs.profile.FollowType
import com.example.thodea.ui.composables.tabs.profile.SettingsScreen
import com.example.thodea.ui.composables.tabs.profile.ThoughtCommentsScreen

/***
* Composable function that defines the navigation screens and their corresponding destinations.
* This is where the NavHost is set up with all the routes.
*
* @param navController The navigation controller used for handling navigation between screens.
* @param modifier Modifier to be applied to the NavHost, typically for padding.
*/
@Composable
fun NavigationScreens(navController: NavHostController, modifier: Modifier = Modifier, isLoggedIn: Boolean,googleAuthClient: AuthClient) {
    // NavHost is where the navigation graph is built
    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) NavItem.Feed.path else NavItem.Login.path,
        modifier = modifier // Apply the padding modifier here
    ) {

        composable(NavItem.Login.path) {
            LoginScreen(

                googleAuthClient = googleAuthClient
                /*onLoginSuccess = {
                    navController.navigate(NavItem.Feed.path) {
                        popUpTo(NavItem.Login.path) { inclusive = true }
                    }
                }*/
            )
        }

        // Define all your composable destinations here
        composable(NavItem.Post.path) {
            PostScreen(onNavigateToFeed = {
                // Example of navigating with popUpTo to clear back stack
                navController.navigate(NavItem.Feed.path) {
                    popUpTo(NavItem.Post.path) {
                        inclusive = true // Remove PostScreen from back stack
                    }
                }
            })
        }
        composable(NavItem.Feed.path) {
            FeedScreen(
                navController = navController
            )
        }

        composable(NavItem.Search.path) { SearchScreen() }
        composable(NavItem.Setup.path) { SetupScreen() }

        // ProfileScreen is your primary screen for triggering follower/following navigation
        composable(NavItem.Profile.path) {
            ProfileScreen(
                onNavigateToSettings = {
                    navController.navigate(NavItem.Settings.path)
                },
                onNavigateToChats = {
                    navController.navigate(NavItem.Chats.path)
                },
                onNavigateToFollowers = {
                    // This navigation call should be safe here as it's triggered by user action
                    // and the NavHost is already composed.
                    navController.navigate(NavItem.FollowInfo.createRoute(FollowType.FOLLOWERS))
                },
                onNavigateToFollowing = {
                    // Same for this navigation call
                    navController.navigate(NavItem.FollowInfo.createRoute(FollowType.FOLLOWING))
                },
                navController = navController,
            )
        }
        composable(NavItem.Settings.path) {
            SettingsScreen(
                onBack = { navController.popBackStack() },
                googleAuthClient = googleAuthClient,
            )
        }
        composable(NavItem.Chats.path) {
            ChatsScreen(
                onBack = { navController.popBackStack() },
                onNavigateToChatRequests = {
                    navController.navigate(NavItem.ChatRequests.path)
                },
                onNavigateToChat = {
                    navController.navigate(NavItem.Chat.path)
                }
            )
        }
        composable(NavItem.ChatRequests.path) {
            ChatRequestsScreen(
                onBack = { navController.popBackStack() }
            )
        }
        composable(NavItem.Chat.path) {
            ChatScreen(
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = NavItem.ThoughtComments.path,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val thoughtId = backStackEntry.arguments?.getString("id")
            ThoughtCommentsScreen(
                thoughtId = thoughtId ?: "",
                onBack = { navController.popBackStack() }
            )
        }

        // The FollowInfo screen with a type argument
        composable(
            route = NavItem.FollowInfo.path,
            arguments = listOf(navArgument("type") { type = NavType.StringType })
        ) { backStackEntry ->
            val typeArg = backStackEntry.arguments?.getString("type")
            val followType = try {
                FollowType.valueOf(typeArg?.uppercase() ?: "FOLLOWERS")
            } catch (e: IllegalArgumentException) {
                // Fallback in case the argument is invalid
                FollowType.FOLLOWERS
            }
            FollowInfoScreen(
                followType = followType,
                onBack = { navController.popBackStack() }
            )
        }
    }
}