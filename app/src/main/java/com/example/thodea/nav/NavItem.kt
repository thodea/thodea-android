package com.example.thodea.nav
import com.example.thodea.R

sealed class NavItem(
    val path: String,
) {
    object Post :
        Item(
            path = NavPath.POST.toString(),
            title = NavTitle.POST,
            icon = R.drawable.ic_post
        )
    object Feed :
        Item(path = NavPath.FEED.toString(), title = NavTitle.FEED, icon =  R.drawable.ic_feed)

    object Search :
        Item(path = NavPath.SEARCH.toString(), title = NavTitle.SEARCH, icon = R.drawable.ic_search)

    object Profile :
        Item(
            path = NavPath.PROFILE.toString(), title = NavTitle.PROFILE, icon = R.drawable.ic_profile)
}