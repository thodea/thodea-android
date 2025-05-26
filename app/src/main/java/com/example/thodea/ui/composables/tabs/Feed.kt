package com.example.thodea.ui.composables.tabs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Composable function that represents the feed screen of the application.
 */

@Preview(showBackground = true)
@Composable
fun FeedScreenPreview() {
    FeedScreen()
}

@Composable
fun FeedScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF111827)), // dark background (#111827)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsPadding(WindowInsets.statusBars)
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FollowToCustomizeFeed()
            Spacer(modifier = Modifier.height(8.dp))
            MonthlyLovedLabel()
            Spacer(modifier = Modifier.height(8.dp))
            MostFollowedLabel()
        }
    }
}

@Composable
fun GradientText(
    text: String,
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight = FontWeight.Normal,
    colors: List<Color>
) {
    Text(
        text = text,
        fontSize = fontSize,
        fontWeight = fontWeight,
        style = TextStyle(
            brush = Brush.verticalGradient(colors = colors)
        )
    )
}

@Composable
fun FollowToCustomizeFeed() {
    Row(
        modifier = Modifier.padding(bottom = 6.dp).alpha(0.9f)
    ) {
        Text(
            text = "follow to customize feed",
            fontSize = 19.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White.copy(alpha = 0.8f),
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 2.dp)
                .background(color = Color(0xFF374151), shape = RoundedCornerShape(8.dp))
                .shadow(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(8.dp),
                    spotColor = Color.Black
                )
                .padding(horizontal = 8.dp, vertical = 2.dp)
        )
    }
}

@Composable
fun MonthlyLovedLabel() {
    val gradientColors = listOf(
        Color(0xFFA855F7), // rgb(168, 85, 247)
        Color(0xFFC084FC), // rgb(192, 132, 252)
        Color(0xFF3B82F6)  // rgb(59, 130, 246)
    )

    Row(
        modifier = Modifier
            .padding(4.dp)
            .background(Color.Transparent)
            .shadow(4.dp, RoundedCornerShape(8.dp))
            .border(
                BorderStroke(
                    0.5.dp,
                    Brush.linearGradient(
                        colors = listOf(Color(0xFF9F7AEA).copy(alpha = 0.5f), Color(0xFF9F7AEA).copy(alpha = 0.5f))
                    )
                ),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 8.dp, vertical = 3.dp)
    ) {
        GradientText(
            text = "monthly loved",
            fontSize = 19.sp,
            fontWeight = FontWeight.SemiBold,
            colors = gradientColors
        )
    }
}

@Composable
fun MostFollowedLabel() {
    val gradientColors = listOf(
        Color(0xFF3B82F6), // rgb(59, 130, 246)
        Color(0xFF60A5FA), // rgb(96, 165, 250)
        Color(0xFFA855F7)  // rgb(168, 85, 247)
    )

    Box(
        modifier = Modifier
            .padding(4.dp)
            .background(Color.Transparent)
            .shadow(4.dp, RoundedCornerShape(8.dp))
            .border(
                BorderStroke(
                    0.5.dp,
                    Brush.linearGradient(
                        colors = listOf(Color(0xFF60A5FA).copy(alpha = 0.5f), Color(0xFF60A5FA).copy(alpha = 0.5f))
                    )
                ),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 8.dp, vertical = 3.dp)
    ) {
        GradientText(
            text = "most followed",
            fontSize = 19.sp,
            fontWeight = FontWeight.SemiBold,
            colors = gradientColors
        )
    }
}

