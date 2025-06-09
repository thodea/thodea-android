package com.example.thodea.ui.composables.tabs.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.zIndex
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class ChatMessage(
    val username: String,
    val message: String,
    val date: Date,
    val id: Int
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
                ChatMessage("Me", "Hello!\nm", Date(), 1),
                ChatMessage("User123", "How are you?", Date(), 2),
                ChatMessage("Me", "I’m fine!", Date(), 3)
            )
        )
    }
    var openDeleteId by remember { mutableStateOf<String?>(null) }
    var inputText by remember { mutableStateOf("") }

    // ⭐️ Function to delete message by ID
    fun deleteMessageById(id: String) {
        messages = messages.filter { it.id.toString() != id }
        openDeleteId = null // close the delete button after deletion
    }

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
                    val trimmedMessage = message.trim()
                    if (trimmedMessage.isNotEmpty()) { // <--- Add this check
                        val newId = (messages.maxOfOrNull { it.id } ?: 0) + 1
                        val newMessage = ChatMessage("Me", trimmedMessage, Date(), newId)
                        messages = messages + newMessage
                        inputText = ""
                    }
                    // If trimmedMessage is empty, nothing happens, effectively preventing sending.
                },
                openDeleteId = openDeleteId,
                // ⭐️ Toggle the delete button for a given message
                onDelete = { messageId ->
                    if (openDeleteId == messageId) {
                        // Delete the message if the delete button is already open
                        deleteMessageById(messageId)
                    } else {
                        // Otherwise, show the delete button
                        openDeleteId = messageId
                    }
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
            .height(32.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,modifier = Modifier) {
        // Left-Aligned Back Icon
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

            IconButton(
                onClick = {},
                modifier = Modifier
                    .height(32.dp)
                    .padding(0.dp)
                    .offset(x = (-12).dp)
                    .clip(RoundedCornerShape(4.dp))


            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .width(28.dp)
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0x2260A5FA))
                    )
                }
            }
            Text(text = "username", color = Color.Gray, fontSize = 18.sp,
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
    onDelete: (String) -> Unit,
    textValue: String,
    openDeleteId: String?,
    onTextChange: (String) -> Unit
) {
    //var input by remember { mutableStateOf("") }
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
                        isCurrentUser = isCurrentUser,
                        onDelete = onDelete,
                        openDeleteId = openDeleteId,
                        messageId = chatMessage.id,
                    )
                }
            }

            // Show red box warning if text exceeds 1000 chars
            if (isLengthExceeded || showPasteError) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(26.dp)
                        .background(Color(0xFFFCA5A5)) // Equivalent to bg-red-300
                ) {
                    Text(
                        text = "1000 char limit",
                        color = Color.Black,
                        modifier = Modifier.align(Alignment.Center),
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center
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
                    onValueChange = { newText ->
                        if (newText.length <= 1000) {
                            onTextChange(newText)
                        } else {
                            // Show error if pasted text would exceed 1000
                            showPasteError = true
                        }
                    },
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
    messageId: Int,
    message: String,
    date: Date,
    isCurrentUser: Boolean,
    openDeleteId: String?,
    onDelete: (String) -> Unit
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
                .shadow(
                    elevation = 2.dp,
                    shape = RoundedCornerShape(8.dp),
                    clip = false,
                    ambientColor = Color.Black.copy(alpha = 1f),
                    spotColor = Color.Black.copy(alpha = 0.5f)
                )
                .background(backgroundColor, shape = RoundedCornerShape(8.dp))
                .padding(8.dp)
        ) {
            // Message text
            Text(
                text = message,
                color = Color.White,
                fontSize = 16.sp
            )

            // Floating Delete button (absolute-like)
            if (isCurrentUser && openDeleteId == messageId.toString()) {
            //if (true) {
                Text(
                    text = "Delete",
                    color = Color.Black,
                    fontSize = 14.sp,
                    lineHeight = 14.sp,
                    modifier = Modifier
                        .background(Color(0xFFe0e0e0), shape = RoundedCornerShape(4.dp))
                        .padding(horizontal = 8.dp, vertical = 1.dp)
                        .align(Alignment.BottomEnd) // ⭐️ changed from TopEnd to BottomEnd
                        .clickable { onDelete(messageId.toString()) }
                        .zIndex(1f)
                )
            }
        }

        // Date and icon row
        Row(
            modifier = Modifier
                .padding(top = 2.dp)
                .align(if (isCurrentUser) Alignment.End else Alignment.Start),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = formattedDate,
                color = Color.Gray,
                lineHeight = 24.sp,
                fontSize = 12.sp,
                modifier = Modifier.offset(x = if (isCurrentUser) 0.dp else 4.dp)
            )

            // Only for current user: delete icon
            if (isCurrentUser) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_msg_delete),
                    contentDescription = "Delete icon",
                    tint = Color(0xFF6b7280),
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            if (openDeleteId == messageId.toString()) {
                                onDelete("") // close
                            } else {
                                onDelete(messageId.toString()) // open
                            }
                        }
                        .padding(start = 4.dp)
                )
            }
        }
    }
}