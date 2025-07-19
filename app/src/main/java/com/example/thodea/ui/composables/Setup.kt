package com.example.thodea.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thodea.R


@Preview(showBackground = true)
@Composable
fun SetupScreenPreview() {
    SetupScreen()
}

@Composable
fun SetupScreen(
    isProcessing: Boolean = false,
    onLogOut: () -> Unit = {}
) {
    // State to hold the username input value
    var username by remember { mutableStateOf("") }

    Scaffold(
        containerColor = Color(0xFF111827) // Dark background (#111827)
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Top Row: "Set username" + Logout Icon
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 2.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Set username",
                        fontSize = 24.sp,
                        color = Color.LightGray,
                        modifier = Modifier.shadow(4.dp, RoundedCornerShape(4.dp))
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.ic_logout),
                        contentDescription = "Logout",
                        tint = Color(0xFF2563EB), // blue-600
                        modifier = Modifier
                            .size(32.dp)
                            .clickable(enabled = !isProcessing) { onLogOut() }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                val usernameTaken by remember { mutableStateOf(false) }

                Row(modifier = Modifier.height(40.dp),
                    verticalAlignment = Alignment.CenterVertically) {

                    Row(modifier = Modifier.height(40.dp)) {
                        if (!usernameTaken) {
                            BasicTextField(
                                maxLines = 1,
                                value = username,
                                onValueChange = {
                                    // Remove disallowed characters: keep only a-z, 0-9, _
                                    val sanitized = it
                                        .lowercase()
                                        .replace(Regex("[^a-z0-9_]"), "")

                                    // Enforce max length 14
                                    username = if (sanitized.length <= 14) {
                                        sanitized
                                    } else {
                                        sanitized.take(14)
                                    }
                                },
                                singleLine = true,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 16.dp, end = 8.dp)
                                    .height(34.dp)
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
                                            .padding(end = 8.dp, bottom = 4.dp)
                                    ) {
                                        if (username.isEmpty()) {
                                            Text(
                                                "username",
                                                fontSize = 24.sp,
                                                color = Color.Gray
                                            )
                                        }
                                        innerTextField()
                                    }
                                }
                            )
                        }  else {
                    Text(
                        text = "username taken",
                        fontSize = 24.sp,
                        color = Color(255, 131, 131),
                        modifier = Modifier
                            .weight(1f) // match BasicTextField weight
                            .padding(start = 16.dp, end = 8.dp)
                            .height(34.dp)
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
                        textAlign = TextAlign.Start
                    )
                }
                    }

                    if (username.isNotEmpty()) {
                        IconButton(
                            onClick = { username = "" },
                            modifier = Modifier
                                .padding(end = 12.dp, start = 0.dp)
                                .size(20.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear text",
                                tint = Color.Gray // Optional: match the text color
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Column(
                        modifier = Modifier
                            .padding(start = 0.dp, top = 2.dp)
                            .shadow(elevation = 4.dp, shape = RoundedCornerShape(4.dp))
                            .clip(RoundedCornerShape(4.dp))
                    ) {
                        Text(
                            text = "a-z, 0-9, _ only 14 char max",
                            fontSize = 15.sp,
                            color = Color.Gray
                        )
                    }
                }

                if (username.trim().isNotEmpty()) {
                    Button(
                        onClick = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(45.dp)
                            .padding(horizontal = 16.dp, vertical = 4.dp)
                            .shadow(elevation = 4.dp, shape = RoundedCornerShape(4.dp)),
                        shape = RoundedCornerShape(4.dp), // ✅ Add this to clip corners
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    color = Color(30, 58, 138),
                                    shape = RoundedCornerShape(4.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Next",
                                fontSize = 22.sp,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}