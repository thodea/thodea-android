package com.example.thodea.ui.composables.tabs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thodea.R

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

    Scaffold(
        containerColor = Color(0xFF111827) // Dark background (#111827)
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
                ) {
                FollowToCustomizeFeed()
                Spacer(modifier = Modifier.height(8.dp))
                MonthlyLovedLabel()
                Spacer(modifier = Modifier.height(8.dp))
                MostFollowedLabel()
                Spacer(modifier = Modifier.height(8.dp))
                Thought()
            }

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
        modifier = Modifier.padding(bottom = 4.dp).alpha(0.9f)
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

@Composable
fun Thought() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp) // give space for the shadow
            .drawBehind {
                val shadowHeight = 16.dp.toPx()
                val cornerRadius = 8.dp.toPx()

                drawRoundRect(
                    color = Color.Black.copy(alpha = 0.2f),
                    topLeft = Offset(0f, size.height - shadowHeight / 2 - 12), // offset upward
                    size = Size(size.width, shadowHeight),
                    cornerRadius = CornerRadius(cornerRadius, cornerRadius)
                )
            }
    ) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF111827)) // Set the actual background color first
            .clip(RoundedCornerShape(8.dp)) // Clip to rounded corners
            .border(
                width = 1.dp,
                color = Color(31, 41, 55),
                shape = RoundedCornerShape(8.dp) // Border with same rounded shape
            )
            .padding(2.dp), // optional: add padding inside

        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column( modifier = Modifier.padding(6.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Row {
                    Box(
                        modifier = Modifier
                            .width(24.dp) // Take remaining space in the Column
                            .aspectRatio(1f) // Make it square
                            .clip(RoundedCornerShape(4.dp)) // Rounded corners
                            .background(Color(0x2260A5FA)) // Tailwind sky-400 style blue
                    )
                }
                Text(text = "username", color = Color.Gray, fontSize = 16.sp,
                    modifier = Modifier.padding(start = 8.dp))
                Spacer(modifier = Modifier.weight(1f))
                //Text(text = "navigation", color = Color.Gray)
                Icon(
                    painter = painterResource(id = R.drawable.ic_nav), // Replace with your actual SVG
                    contentDescription = "Image icon",
                    tint = Color(0xFF9CA3AF), // Adjust color as needed
                    modifier = Modifier
                        .size(24.dp)
                )
            }
            Row(modifier = Modifier.padding(top = 10.dp)) {
                Text(text = "Main text", color = Color.Gray, fontSize = 16.sp)
            }
            Row(modifier = Modifier.padding(top = 8.dp),verticalAlignment = Alignment.CenterVertically) {
                //Text(text = "Love", color = Color.Gray)
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,                    contentDescription = "Image icon",
                    tint = Color(0xFF9ca3af), // Adjust color as needed
                    modifier = Modifier
                        .size(18.dp).padding(top = 1.dp)
                )
                Text(text = "0", color = Color.Gray,
                    modifier = Modifier.padding(start = 8.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_comment), // Replace with your actual SVG
                    contentDescription = "Image icon",
                    tint = Color(0xFF9ca3af), // Adjust color as needed
                    modifier = Modifier
                        .size(24.dp).padding(start = 8.dp, top = 2.dp)
                )
                //Text(text = "Comments", color = Color.Gray,
                //    modifier = Modifier.padding(start = 8.dp))
                Text(text = "0", color = Color.Gray,
                    modifier = Modifier.padding(start = 10.dp))

            }
            Row(modifier = Modifier.padding(top = 4.dp),verticalAlignment = Alignment.CenterVertically) {
                //Text(text = "Views", color = Color.Gray)
                Icon(
                    painter = painterResource(id = R.drawable.ic_seen), // Replace with your actual SVG
                    contentDescription = "Image icon",
                    tint = Color(0xFF6b7280), // Adjust color as needed
                    modifier = Modifier
                        .size(18.dp).padding(top = 2.dp)
                )
                Text(text = "1", color = Color.Gray, fontSize = 14.sp,
                    modifier = Modifier.padding(start = 4.dp))
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "Date", color = Color.Gray)
            }
        }
    }
        }
}