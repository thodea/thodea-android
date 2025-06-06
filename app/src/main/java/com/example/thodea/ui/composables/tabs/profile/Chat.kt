package com.example.thodea.ui.composables.tabs.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thodea.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

@Preview(showBackground = true)
@Composable
fun ChatScreenPreview() {
    ChatScreen(onBack = {}, onNavigateToChatRequests = {})
}

@Composable
fun ChatScreen(
    onBack: () -> Unit,
    onNavigateToChatRequests: () -> Unit
) {
    // Example list of messages - in real usage, use a ViewModel!
    var messages by remember { mutableStateOf(listOf("Hello!", "How are you?", "I’m fine!")) }

    Scaffold(
        containerColor = Color(0xFF111827) // Dark background
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            ChatMainLayout(
                onBack = onBack,
                onNavigateToChatRequests = onNavigateToChatRequests,
                messages = messages,
                onSendMessage = { message ->
                    // Update the messages list when a new message is sent
                    messages = messages + message
                }
            )
        }
    }
}

@Composable
fun ChatTopRow(onClick: () -> Unit, onNavigateToChatRequests: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,modifier = Modifier) {
            IconButton(
                onClick = onClick,
                modifier = Modifier
                    .height(24.dp)
                    .padding(0.dp)
                    .offset(x = (-12).dp)

            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .width(24.dp)
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0x2260A5FA))
                    )
                }
            }
            Text(text = "username", color = Color.Gray, fontSize = 16.sp,
                modifier = Modifier.offset(x = (-16).dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            // Right-Aligned Requests Icon
            IconButton(
                onClick = onNavigateToChatRequests,
                modifier = Modifier
                    .height(34.dp)
                    .padding(0.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_delete),
                    contentDescription = "Delete",
                    tint = Color(0xFF6b7280),
                    modifier = Modifier.size(40.dp)
                )
            }
        }



    }
}

@Composable
fun ChatMainLayout(
    onBack: () -> Unit,
    onNavigateToChatRequests: () -> Unit,
    messages: List<String>, // Example list of messages
    onSendMessage: (String) -> Unit
) {
    var input by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ChatTopRow(
            onClick = onBack,
            onNavigateToChatRequests = onNavigateToChatRequests
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Main area for messages + input field
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Chat messages list
            LazyColumn(
                modifier = Modifier
                    .weight(1f) // Fill remaining space
                    .fillMaxWidth(),
                reverseLayout = true // Show latest messages at the bottom
            ) {
                items(messages.size) { index ->
                    val message = messages[messages.size - 1 - index] // Reverse for display
                    ChatMessageItem(message = message)
                }
            }

            // Input field at the bottom
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = input,
                    onValueChange = { input = it },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("Type a message...") }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    if (input.isNotBlank()) {
                        onSendMessage(input)
                        input = ""
                    }
                }) {
                    Text("Send")
                }
            }
        }
    }
}

@Composable
fun ChatMessageItem(message: String) {
    // Customize how each chat message looks
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Text(text = message)
    }
}