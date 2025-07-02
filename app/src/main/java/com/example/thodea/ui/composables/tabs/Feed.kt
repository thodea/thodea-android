package com.example.thodea.ui.composables.tabs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.navigation.NavController
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
fun FeedScreen(navController: NavController? = null) {
    // State to track which thought is currently selected
    var selectedThoughtId by remember { mutableStateOf<String?>(null) }

    // Sample thoughts for demonstration
    val thoughts = remember {
        listOf(
            Thought(id = "1", urlTitle = "Jetpack Compose Basics", urlDescription = "A modern toolkit for building native Android UIs.", authorId = "alice42"),
            Thought(id = "2", urlTitle = "Android Development", urlDescription = "Building amazing mobile apps", authorId = "john_dev"),
            Thought(id = "3", urlTitle = "UI Design Patterns", urlDescription = "Modern design patterns for mobile apps", authorId = "designer_mike")
        )
    }

    var thoughtsList by remember { mutableStateOf(thoughts) }

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

                // Display all thoughts
                thoughtsList.forEach { thought ->
                    Thought(
                        username = thought.authorId ?: "unknown",
                        thoughtId = thought.id,
                        thought = thought,
                        isSelected = selectedThoughtId == thought.id,
                        onThoughtClick = { id ->
                            // Toggle selection: if already selected, deselect; otherwise select
                            selectedThoughtId = if (selectedThoughtId == id) null else id
                        },
                        onNavigateToComments = {
                            navController?.navigate("thought/${thought.id}")
                        },
                        onLoveClick = { id -> println("Loved thought: $id") },
                        onUsernameClick = { },
                        onDeleteClick = { id ->
                            // Remove thought from list
                            thoughtsList = thoughtsList.filter { it.id != id }
                            // Clear selection
                            selectedThoughtId = null
                        },
                        borderSwitch = true,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
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
fun Thought(
    username: String,
    thoughtId: String,
    thought: Thought,
    isSelected: Boolean = false,
    onThoughtClick: (thoughtId: String) -> Unit,
    onNavigateToComments: () -> Unit = {},
    onLoveClick: (thoughtId: String) -> Unit,
    onUsernameClick: (username: String) -> Unit,
    onDeleteClick: (thoughtId: String) -> Unit = {},
    borderSwitch: Boolean
) {
    var showDropdownMenu by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .then(
                if (borderSwitch) {
                    Modifier.drawBehind {
                        val shadowHeight = 16.dp.toPx()
                        val cornerRadius = 8.dp.toPx()

                        drawRoundRect(
                            color = Color.Black.copy(alpha = 0.2f),
                            topLeft = Offset(0f, size.height - shadowHeight / 2 - 12),
                            size = Size(size.width, shadowHeight),
                            cornerRadius = CornerRadius(cornerRadius, cornerRadius)
                        )
                    }
                } else {
                    Modifier
                }
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    if (isSelected) Color(0xFF1F2937).copy(alpha = 0.8f) // Highlighted background when selected
                    else Color(0xFF111827)
                )
                .clip(RoundedCornerShape(8.dp))
                .border(
                    width = if (isSelected) 2.dp else 1.dp,
                    color = if (isSelected) Color(0xFF3B82F6) // Blue border when selected
                    else if (borderSwitch) Color(31, 41, 55)
                    else Color.Transparent,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(2.dp)
                .clickable(onClick = { onThoughtClick(thoughtId) }), // Whole container clickable
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .then(
                        if (borderSwitch) Modifier.padding(6.dp) else Modifier
                    )
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .width(24.dp)
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0x2260A5FA))
                    )

                    Text(
                        text = username,
                        color = Color.Gray,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .clickable(onClick = {
                                // Avoid propagating click to Thought
                                onUsernameClick(username)
                            })
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    if (borderSwitch && !isSelected) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_nav),
                            contentDescription = "Image icon",
                            tint = Color(0xFF9CA3AF),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                Row(modifier = Modifier.padding(top = 10.dp, bottom = 4.dp)) {
                    Text(text = "Main text", color = Color(222, 220, 220, 255), fontSize = 16.sp)
                }

                ThoughtUrlDisplay(thought = thought)

                Row(
                    modifier = Modifier.padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Love",
                        tint = Color(0xFF9ca3af),
                        modifier = Modifier
                            .size(18.dp)
                            .padding(top = 1.dp)
                            .clickable(onClick = {
                                // Only trigger love click
                                onLoveClick(thoughtId)
                            })
                    )
                    Text(
                        text = "0",
                        color = Color.Gray,
                        modifier = Modifier.padding(start = 8.dp)
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.ic_comment),
                        contentDescription = "Comment",
                        tint = Color(0xFF9ca3af),
                        modifier = Modifier
                            .size(24.dp)
                            .padding(start = 8.dp, top = 2.dp)
                            .clickable {
                                onNavigateToComments()
                            }
                    )
                    Text(
                        text = "0",
                        color = Color.Gray,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }

                Row(
                    modifier = Modifier.padding(top = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "1",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_seen),
                        contentDescription = "Seen",
                        tint = Color(0xFF6b7280),
                        modifier = Modifier.size(18.dp).padding(top = 2.dp).padding(start = 4.dp)
                    )

                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "0",
                        color = Color.Gray,
                        fontSize = 14.sp,
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_link),
                        contentDescription = "Seen",
                        tint = Color(0xFF6b7280),
                        modifier = Modifier.size(18.dp).padding(top = 2.dp).padding(start = 4.dp)
                    )

                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "Date", color = Color.Gray)
                }
            }

            // Three dots menu - only show when thought is selected
            if (isSelected) {
                Box {
                    IconButton(
                        onClick = { showDropdownMenu = true },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "More options",
                            tint = Color(0xFF9CA3AF),
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    DropdownMenu(
                        expanded = showDropdownMenu,
                        onDismissRequest = { showDropdownMenu = false },
                        modifier = Modifier.background(Color(0xFF1F2937))
                    ) {
                        DropdownMenuItem(
                            text = {
                                Text(
                                    "Delete",
                                    color = Color(0xFFEF4444) // Red color for delete
                                )
                            },
                            onClick = {
                                showDropdownMenu = false
                                onDeleteClick(thoughtId)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ThoughtUrlDisplay(thought: Thought) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(top = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (!thought.urlTitle.isNullOrEmpty() || !thought.urlDescription.isNullOrEmpty()) {
            Column {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .fillMaxHeight()
                        .background(Color(0xFF1E3A8A), shape = RoundedCornerShape(2.dp)) // blue-950
                )
            }
        }

        Column(
            modifier = Modifier
                .then(
                    if (!thought.urlTitle.isNullOrEmpty() || !thought.urlDescription.isNullOrEmpty()) {
                        Modifier
                            .padding(start = 4.dp)
                            .fillMaxWidth()
                    } else {
                        Modifier.fillMaxWidth()
                    }
                )
        ) {
            thought.urlTitle?.let {
                Text(
                    text = it,
                    maxLines = 1,
                    color = Color.Gray
                )
            }
            thought.urlDescription?.let {
                Text(
                    text = it,
                    maxLines = 1,
                    color = Color.Gray
                )
            }
        }
    }
}

data class Thought(
    val id: String,
    val urlTitle: String? = null,
    val urlDescription: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val authorId: String? = null
)
