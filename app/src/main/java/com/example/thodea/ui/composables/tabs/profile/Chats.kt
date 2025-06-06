package com.example.thodea.ui.composables.tabs.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thodea.R


@Preview(showBackground = true)
@Composable
fun ChatsScreenPreview() {
    ChatsScreen(onBack = {}, onNavigateToChatRequests = {})
}

@Composable
fun ChatsScreen(onBack: () -> Unit,onNavigateToChatRequests: () -> Unit) {
    Scaffold(
        containerColor = Color(0xFF111827) // Dark background (#111827)
    )  { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            ChatsRowLayout(onBack = onBack, onNavigateToChatRequests = onNavigateToChatRequests)
        }
    }
}
@Composable
fun BackSettingsButton(onClick: () -> Unit, onNavigateToChatRequests: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(34.dp),
        contentAlignment = Alignment.Center
    ) {
        // Centered Text
        Text(
            fontSize = 24.sp,
            text = "Chats",
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

        // Right-Aligned Requests Icon
        IconButton(
            onClick = onNavigateToChatRequests,
            modifier = Modifier
                .height(34.dp)
                .align(Alignment.CenterEnd)
                .padding(0.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_requests),
                contentDescription = "Requests",
                tint = Color(0xFF1d4ed8),
                modifier = Modifier.size(40.dp)
            )
        }
    }
}

@Composable
fun ChatsRowLayout(onBack: () -> Unit,onNavigateToChatRequests: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        BackSettingsButton(onClick = onBack, onNavigateToChatRequests = onNavigateToChatRequests)
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp) // 8dp space between items
        ) {
            Chat()
        }
    }
}

@Composable
fun Chat() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
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
                Text(text = "Date", color = Color.Gray)
            }
            Row(modifier = Modifier.padding(top = 10.dp)) {
                Text(text = "Last message...", color = Color.Gray, fontSize = 16.sp)
            }

        }
    }
}