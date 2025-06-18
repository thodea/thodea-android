package com.example.thodea.ui.composables.tabs.profile


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thodea.R
import com.example.thodea.ui.composables.tabs.Thought


@Preview(showBackground = true)
@Composable
fun ThoughtCommentsScreenPreview() {
    ThoughtCommentsScreen(onBack = {})
}

@Composable
fun ThoughtCommentsScreen(onBack: () -> Unit) {
    Scaffold(
        containerColor = Color(0xFF111827) // Dark background (#111827)
    )  { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            ThoughtCommentsRowLayout(onBack = onBack)
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
fun ThoughtCommentsRowLayout(onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        BackThoughtCommentsButton(onClick = onBack)
        Spacer(modifier = Modifier.height(12.dp))
        Thought(
            username = "alice42",
            thoughtId = "abc123",
            //onThoughtClick = { id -> navController.navigate("comments/$id") },
            onThoughtClick = {  },
            onLoveClick = { id -> println("Loved thought: $id") },
            //onUsernameClick = { name -> navController.navigate("profile/$name") }
            onUsernameClick = { },
            borderSwitch = false,
        )
    }
}