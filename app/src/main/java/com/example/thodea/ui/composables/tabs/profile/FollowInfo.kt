package com.example.thodea.ui.composables.tabs.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thodea.R

enum class FollowType {
    FOLLOWERS,
    FOLLOWING
}

@Preview(showBackground = true)
@Composable
fun FollowInfoScreenPreview() {
    FollowInfoScreen(followType = FollowType.FOLLOWERS, onBack = {})
}

@Composable
fun FollowInfoScreen(followType: FollowType, onBack: () -> Unit) {
    Scaffold(
        containerColor = Color(0xFF111827) // Dark background (#111827)
    )  { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Column(modifier = Modifier
                .fillMaxSize().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                FirstRowFollowInfoLayout(followType = followType, onBack = onBack)
                Spacer(modifier = Modifier.height(8.dp))
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp) // 8dp space between items
                ) {
                    Follow(onNavigateToUser = {})
                }
            }
        }
    }
}


@Composable
fun FollowInfoBackButton(followType: FollowType, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(34.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .height(34.dp)
                .offset(x = (-8).dp)
                .padding(0.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Back",
                tint = Color(0xFF075985),
                modifier = Modifier.size(30.dp)
            )
        }

        Text(
            text = "username",
            fontSize = 18.sp,
            color = Color(229, 231, 235),
            style = MaterialTheme.typography.titleMedium,
            lineHeight = 20.sp,
            modifier = Modifier
                .offset(y = (-1).dp)
        )

        Text(
            text = when (followType) {
                FollowType.FOLLOWERS -> " /followers"
                FollowType.FOLLOWING -> " /following"
            },
            fontSize = 18.sp,
            color = Color(229, 231, 235),
            lineHeight = 20.sp,
            modifier = Modifier
                .offset(y = (-2).dp)
        )
    }
}

@Composable
fun FirstRowFollowInfoLayout(followType: FollowType, onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        FollowInfoBackButton(followType = followType, onClick = onBack)
        Spacer(modifier = Modifier.height(8.dp))
    }
}


@Composable
fun Follow(onNavigateToUser: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp) // give space for the shadow
            .drawBehind {
                val shadowHeight = 16.dp.toPx()
                val cornerRadius = 8.dp.toPx()

                drawRoundRect(
                    color = Color.Black.copy(alpha = 0.2f),
                    topLeft = Offset(0f, size.height - shadowHeight / 2 - 14), // offset upward
                    size = Size(size.width, shadowHeight),
                    cornerRadius = CornerRadius(cornerRadius, cornerRadius)
                )
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF111827)) // Set the actual background color first
                .clip(RoundedCornerShape(8.dp)) // Clip the content
                .border(
                    width = 1.dp,
                    color = Color(31, 41, 55),
                    shape = RoundedCornerShape(8.dp) // Border with same rounded shape
                )
                .padding(2.dp)
                .clickable(onClick = onNavigateToUser), // optional: add padding inside

            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.padding(6.dp)) {
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
                    Text(
                        text = "username | 0", color = Color.Gray, fontSize = 16.sp,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "2", color = Color.Gray, fontSize = 16.sp,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_follow),
                        contentDescription = "Send",
                        tint = Color(0xFF3B82F6),
                        modifier = Modifier.size(24.dp) // Resize icon to fit within 34.dp IconButton
                    )
                }
                Row(modifier = Modifier.padding(top = 10.dp)) {
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "Date", color = Color.Gray)
                }

            }
        }
    }
}