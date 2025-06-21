package com.example.thodea.ui.composables.tabs.profile


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thodea.R
import com.example.thodea.ui.composables.tabs.Thought
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds


@Preview(showBackground = true)
@Composable
fun ThoughtCommentsScreenPreview() {
    ThoughtCommentsScreen(thoughtId = "1", onBack = {})
}

@Composable
fun ThoughtCommentsScreen( thoughtId: String, onBack: () -> Unit) {
    var inputText by remember { mutableStateOf("") }
    Scaffold(
        containerColor = Color(0xFF111827) // Dark background (#111827)
    )  { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            ThoughtCommentsRowLayout(
                onBack = onBack,
                textValue = inputText,
                onTextChange = { inputText = it },
                thoughtId = thoughtId,
            )
        }
    }
}

@Composable
fun BackThoughtCommentsButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(34.dp),
        contentAlignment = Alignment.Center
    ) {
        // Centered Text
        Text(
            fontSize = 22.sp,
            text = "Comments",
            color = Color(229, 231, 235),
            style = MaterialTheme.typography.titleMedium
        )

        // Left-Aligned Back Icon
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .height(34.dp)
                .offset(x = (-8).dp)
                .align(Alignment.CenterStart)
                .padding(0.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Back",
                tint = Color(0xFF075985),
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@Composable
fun ThoughtCommentsRowLayout(onBack: () -> Unit,
                             textValue: String,
                             onTextChange: (String) -> Unit,
                             thoughtId: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        BackThoughtCommentsButton(onClick = onBack)
        Spacer(modifier = Modifier.height(12.dp))
        Thought(
            username = "alice42",
            thoughtId = thoughtId,
            //onThoughtClick = { id -> navController.navigate("comments/$id") },
            onThoughtClick = {  },
            onLoveClick = { id -> println("Loved thought: $id") },
            //onUsernameClick = { name -> navController.navigate("profile/$name") }
            onUsernameClick = { },
            borderSwitch = false,
        )
        Spacer(modifier = Modifier.height(2.dp))

        var isLengthExceeded by remember { mutableStateOf(false) }
        var showPasteError by remember { mutableStateOf(false) }

        // Whenever showPasteError becomes true, auto-dismiss after 2 seconds
        LaunchedEffect(showPasteError) {
            if (showPasteError) {
                delay(2.seconds)
                showPasteError = false
            }
        }

        LaunchedEffect(isLengthExceeded) {
            if (isLengthExceeded) {
                delay(2.seconds)
                isLengthExceeded = false
            }
        }

        BasicTextField(
            value = textValue,
            onValueChange = { newText ->
                val sanitized = newText.replace("\n", " ")
                if (sanitized.length <= 150) { // Limit text input to 3000
                    onTextChange(sanitized)
                } else {
                    // Show error if pasted text would exceed 1000
                    showPasteError = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp)
                .heightIn(min = 36.dp) // expands as needed
                .drawBehind {
                    val strokeWidth = 1.dp.toPx()
                    val y = size.height
                    drawLine(
                        color = Color(30, 58, 138), // blue-950
                        start = Offset(0f, y),
                        end = Offset(size.width, y),
                        strokeWidth = strokeWidth
                    )
                },
            textStyle = TextStyle(
                fontSize = 24.sp,
                color = Color(229, 231, 235)
            ),
            cursorBrush = SolidColor(Color(229, 231, 235)),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 8.dp, bottom = 4.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (textValue.isEmpty()) {
                        Text(
                            "Comment",
                            fontSize = 24.sp,
                            color = Color.Gray
                        )
                    }
                    innerTextField()
                }
            }
        )


        // Row 3: Media input
        Box(
            contentAlignment = Alignment.Center,
            //horizontalArrangement = Arrangement.Center,
            //verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { } )
                .padding(end = 0.dp, top = 4.dp) // pr-8 in Tailwind (8 * 4 = 32)
        ) {
            // SVG icon - you'll need to add the vector asset to your project
            Icon(
                painter = painterResource(id = R.drawable.ic_media_input), // Replace with your actual SVG
                contentDescription = "Image icon",
                tint = Color(0xFF3B82F6), // Adjust color as needed
                modifier = Modifier
                    .width(24.dp)
                    .height(24.dp)
                    .align(Alignment.CenterStart)
            )

            if (isLengthExceeded || showPasteError) {
                Text(
                    text = "150 char limit",
                    color = Color(0xFFBE5A5A), // text-red-500
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                )
            }

            Text(
                text = "reset",
                fontSize = 20.sp,
                color = Color(0xFF8B8B8B),
                modifier = Modifier
                    .alpha(if (textValue.trim().isNotEmpty()) 1f else 0f)
                    .align(Alignment.CenterEnd)
                    .padding(end = 16.dp)
                    .clickable { onTextChange("")}
            )

            Spacer(modifier = Modifier.width(8.dp)) // spacing between text and circle

            Box(
                modifier = Modifier
                    .alpha(if (textValue.trim().isNotEmpty()) 1f else 0f)
                    .padding(top = 2.dp)
                    .align(Alignment.CenterEnd)
                    .size(8.dp) // tiny circle
                    .clip(CircleShape)
                    .background(Color(147, 197, 253)) // Tailwind-style blue-500
            )

            /*if (isLengthExceeded || showPasteError) {
                Text(
                    text = "3000 char limit",
                    color = Color(0xFFBE5A5A), // text-red-500
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                )
            }*/

        }

        val canPost = textValue.trim().isNotEmpty() // or your own logic

        if (canPost) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(top = 12.dp)
                    .shadow(
                        elevation = 4.dp,
                        shape = RoundedCornerShape(8.dp),
                        // Optional: adjust these for different shadow appearances
                        clip = false, // set to true if you want content to respect the rounded corners
                        ambientColor = Color.Black,
                        spotColor = Color.Black
                    )
                    .clip(RoundedCornerShape(8.dp))
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF3B82F6), // blue-500
                                Color(0xFF1E40AF)  // Light blue bottom
                            )
                        )
                    )
                    .clickable {
                        // call your post handler here
                        // handlePost(userName, textValue, assetExtension)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "COMMENT",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(229, 231, 235)
                )
            }
        }

        Spacer(modifier = Modifier.height(2.dp))

        var selectedTab by remember { mutableStateOf("recent") }

        TabSelector(selectedTab = selectedTab, selectTab = { selectedTab = it })

        Comment(
            username = "john doe",
            date = "2025-06-21",
            comment = "This is a short comment." // or a long one to see dynamic growth
        )
    }
}

@Composable
fun TabSelector(
    selectedTab: String,
    selectTab: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val tabs = listOf("recent", "popular")

        tabs.forEach { tab ->
            val isSelected = selectedTab == tab
            val borderColor = if (isSelected) Color(7, 89, 133)
            else Color.Transparent
            val textStyle = if (isSelected) {
                MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
            } else {
                MaterialTheme.typography.bodyMedium
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable { selectTab(tab) }
                    .padding(bottom = 8.dp) // Adds space between text and border
                    .drawBehind {
                        if (isSelected) {
                            val strokeWidth = 2.dp.toPx()
                            val y = size.height // Bottom edge
                            drawLine(
                                color = borderColor,
                                start = Offset(0f, y),
                                end = Offset(size.width, y),
                                strokeWidth = strokeWidth
                            )
                        }
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = tab.replaceFirstChar { it.uppercase() },
                    style = textStyle,
                    fontSize = 16.sp,
                    color = Color(238, 232, 232, 255),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
        }
    }
}

@Composable
fun Comment(username: String, date: String, comment: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(bottom = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF111827)) // Background
                .padding(start = 4.dp)
        ) {
            // Left border
            Box(
                modifier = Modifier
                    .width(2.dp)
                    .fillMaxHeight()
                    .background(Color(0xFF1F2937))
            )

            Column(
                modifier = Modifier
                    .padding(6.dp)
                    .fillMaxWidth()
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
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = date, color = Color.Gray)
                }

                Text(
                    text = comment,
                    color = Color.Gray,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 10.dp),
                    softWrap = true // Wrap long comments naturally
                )
            }
        }
    }
}