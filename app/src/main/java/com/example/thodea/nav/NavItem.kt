package com.example.thodea.nav
import com.example.thodea.R
import com.example.thodea.ui.composables.tabs.profile.FollowType

sealed class NavItem(
    val path: String,
) {
    object Post :
        Item(path = NavPath.POST.toString(), title = NavTitle.POST, icon = R.drawable.ic_post)
    object Feed :
        Item(path = NavPath.FEED.toString(), title = NavTitle.FEED, icon =  R.drawable.ic_feed)
    object Search :
        Item(path = NavPath.SEARCH.toString(), title = NavTitle.SEARCH, icon = R.drawable.ic_search)
    object Profile :
        Item(path = NavPath.PROFILE.toString(), title = NavTitle.PROFILE, icon = R.drawable.ic_profile)
    // Sub-pages (not in bottom nav)
    object Settings : NavItem(
        path = "settings"
    )
    object Chats : NavItem(
        path = "chats"
    )
    object Login : NavItem(
        path = "login"
    )
    object ChatRequests : NavItem(
        path = "chat requests"
    )
    object Chat : NavItem(
        path = "chat"
    )
    object FollowInfo : NavItem("followInfo/{type}") {
        fun createRoute(type: FollowType): String = "followInfo/${type.name}"
    }
    object ThoughtComments : NavItem("thought/{id}") {
        //fun createRoute(id: String): String = "thought/$id"
    }
}