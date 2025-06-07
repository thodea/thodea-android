package com.example.thodea.ui.composables.tabs.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class ChatMessage(
    val username: String,
    val message: String,
    val date: Date
)

@Preview(showBackground = true)
@Composable
fun ChatScreenPreview() {
    ChatScreen(onBack = {})
}

@Composable
fun ChatScreen(
    onBack: () -> Unit,
    //onNavigateBackToChats: () -> Unit
) {
    // Example list of messages - in real usage, use a ViewModel!
    var messages by remember {
        mutableStateOf(
            listOf(
                ChatMessage("Me", "Hello!", Date()),
                ChatMessage("User123", "How are you?", Date()),
                ChatMessage("Me", "I’m fine!", Date())
            )
        )
    }
    var inputText by remember { mutableStateOf("") }

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
                //onNavigateBackToChats = onNavigateBackToChats,
                messages = messages,
                onSendMessage = { message ->
                    val newMessage = ChatMessage("Me", message, Date())
                    messages = messages + newMessage
                },
                textValue = inputText,
                onTextChange = { inputText = it }
            )
        }
    }
}

@Composable
fun ChatTopRow(onClick: () -> Unit) {
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
                onClick = { },
                modifier = Modifier
                    .height(34.dp)
                    .padding(0.dp)
                    .offset(10.dp)
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
    //onNavigateToChat: () -> Unit,
    messages: List<ChatMessage>, // Example list of messages
    onSendMessage: (String) -> Unit,
    textValue: String,
    onTextChange: (String) -> Unit
) {
    //var input by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            ChatTopRow(
                onClick = onBack,
                //onNavigateToChat = onNavigateToChat
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        // Main area for messages + input field
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Chat messages list
            LazyColumn(
                modifier = Modifier
                    .weight(1f) // Fill remaining space
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 8.dp),
                reverseLayout = true // Show latest messages at the bottom
            ) {
                items(messages.size) { index ->
                    val chatMessage = messages[messages.size - 1 - index]
                    val isCurrentUser = chatMessage.username == "Me"

                    ChatMessageItem(
                        message = chatMessage.message,
                        date = chatMessage.date,
                        isCurrentUser = isCurrentUser
                    )
                }
            }

            // Input field at the bottom
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = {},
                    //onClick = onNavigateToChat,
                    modifier = Modifier
                        .height(34.dp)
                        .padding(0.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_media_input),
                        contentDescription = "Send",
                        tint = Color(0xFF3B82F6),
                        modifier = Modifier.size(24.dp) // Resize icon to fit within 34.dp IconButton
                    )
                }

                BasicTextField(
                    maxLines = 7,
                    value = textValue,
                    onValueChange = onTextChange,
                    modifier = Modifier
                        .weight(1f) // Takes remaining space
                        .drawBehind {
                            val strokeWidth = 1.dp.toPx()
                            val y = size.height
                            drawLine(
                                color = Color(30, 58, 138),
                                start = Offset(0f, y),
                                end = Offset(size.width, y),
                                strokeWidth = strokeWidth
                            )
                        },
                    textStyle = TextStyle(
                        fontSize = 24.sp,
                        color = Color(229, 231, 235),
                    ),
                    cursorBrush = SolidColor(Color(229, 231, 235)),
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 4.dp)
                        ) {
                            if (textValue.isEmpty()) {
                                Text(
                                    "Message",
                                    fontSize = 24.sp,
                                    color = Color.Gray,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                            innerTextField()
                        }
                    }
                )
                IconButton(
                    onClick = { onSendMessage(textValue) },
                    modifier = Modifier
                        .height(34.dp)
                        .padding(0.dp)
                        /*.border(
                            width = 1.dp,
                            color = Color(31, 41, 55),
                            shape = RoundedCornerShape(8.dp) // Border with same rounded shape
                        )*/
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_send),
                        contentDescription = "Send",
                        tint = Color(0xFF6b7280),
                        modifier = Modifier.size(24.dp) // Resize icon to fit within 34.dp IconButton
                    )
                }
            }
        }
    }
}


@Composable
fun ChatMessageItem(
    message: String,
    date: Date,
    isCurrentUser: Boolean
) {
    val backgroundColor = if (isCurrentUser) {
        Color(23, 37, 84)
    } else {
        Color(30, 41, 59)
    }

    val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    val formattedDate = dateFormat.format(date)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalAlignment = if (isCurrentUser) Alignment.End else Alignment.Start
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .background(backgroundColor, shape = RoundedCornerShape(8.dp))
                .padding(8.dp)
        ) {
            Column {
                Text(
                    text = message,
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
        Text(
            text = formattedDate,
            color = Color.Gray,
            fontSize = 12.sp,
            modifier = Modifier
                .padding(top = 6.dp)
                .offset(x = if (isCurrentUser) 0.dp else 4.dp)
        )
    }
}