package com.example.thodea.ui.composables.tabs.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thodea.R


@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(onBack = {})
}

@Composable
fun SettingsScreen(onBack: () -> Unit) {
    Scaffold(
        containerColor = Color(0xFF111827) // Dark background (#111827)
    )  { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            FirstRowLayout(onBack = onBack)
        }
    }
}

@Composable
fun BackButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(34.dp)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        // Centered Text
        Text(
            fontSize = 24.sp,
            text = "Settings",
            color = Color(229, 231, 235),
            style = MaterialTheme.typography.titleMedium // or style you prefer
        )

        // Left-Aligned Back Icon
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .height(34.dp)
                .offset(x = (-8).dp)
                .align(Alignment.CenterStart) // Puts the icon on the left
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
fun FirstRowLayout(onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 4.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(16.dp),
    ) {
        BackButton(onClick = onBack)
        Spacer(modifier = Modifier.height(8.dp))
        SettingsItem(title = "Add Bio", onClick = { })
        SettingsItem(title = "LOG OUT", onClick = { })
        SettingsItem(title = "DELETE", onClick = { })
        SettingsItem(title = "users: ", onClick = { })
        SettingsItem(title = "About", onClick = { })
        Spacer(modifier = Modifier.weight(1f)) // pushes text to bottom
        // Bottom Text
        BottomLinks(
            onTermsClick = {},
            onPrivacyClick = {}
        )
    }
}

@Composable
fun BottomLinks(
    onTermsClick: () -> Unit,
    onPrivacyClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            // Terms Link
            Text(
                text = "Terms",
                color = Color(0xFF3B82F6), // blue-500
                fontSize = 20.sp,
                modifier = Modifier
                    .clickable { onTermsClick() }
                    .padding(8.dp)
            )

            // Privacy Link
            Text(
                text = "Privacy",
                color = Color(0xFF3B82F6), // blue-500
                fontSize = 20.sp,
                modifier = Modifier
                    .clickable { onPrivacyClick() }
                    .padding(8.dp)
            )
        }
    }
}

@Composable
fun SettingsItem(title: String, onClick: () -> Unit) {
    val isBlackItem = title != "Add Bio" && title != "users: "
    val textColor = when {
        title == "users: " -> Color(156, 163, 175)
        title != "Add Bio" -> Color.Black
        else -> Color(229, 231, 235)
    }

    val boxColorBrush = when (title) {
        "LOG OUT" -> Brush.linearGradient(listOf(Color(0xFF93C5FD), Color(0xFF93C5FD))) // solid color
        "DELETE" -> Brush.linearGradient(listOf(Color(0xFFFCA5A5), Color(0xFFFCA5A5))) // solid color
        else -> Brush.linearGradient(
            colors = listOf(Color(0xFF93C5FD), Color(0xFF818CF8)),
            start = Offset.Zero,
            end = Offset(150f, 110f) // diagonal "to bottom left"
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .let {
                if (title != "users: ") it.clickable { onClick() } else it
            }
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        if (isBlackItem) {
            Box(
                modifier = Modifier
                    .background(brush = boxColorBrush, shape = RoundedCornerShape(4.dp)) // blue bg + rounded corners
                    .padding(horizontal = 8.dp, vertical = 1.dp) // add padding around text
            ) {
                Text(
                    title,
                    fontSize = 18.sp,
                    style = MaterialTheme.typography.bodyLarge,
                    color = textColor,
                    fontWeight = FontWeight.Bold
                )
            }
        } else {
            Text(
                text = title,
                fontSize = 18.sp,
                color = textColor,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = if (title != "users: ") FontWeight.Bold else FontWeight.Normal
                )
            )

        if (title == "Add Bio") {
            Spacer(modifier = Modifier.width(8.dp)) // spacing between text and circle

            Box(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(Color(147, 197, 253)) // Tailwind-style blue-500
            )
        }
    }
        }
}