package com.example.thodea.ui.composables.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.thodea.R
import com.example.thodea.ui.theme.typography

/**
 * Composable function that represents the feed screen of the application.
 */
@Composable
fun FeedScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF111827)), // dark background (#111827)
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.feed),
            style = typography.titleLarge,
            color = Color(0xFF6B7280) // text color (#6B7280)
        )
    }
}