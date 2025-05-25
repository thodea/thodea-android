package com.example.thodea.ui.composables.tabs

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.SolidColor

@Composable
fun PostScreen(
    onNavigateToFeed: () -> Unit
) {
    var inputText by remember { mutableStateOf("") }

    Scaffold(
        containerColor = Color(0xFF111827) // Dark background (#111827)
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ThreeRowLayout(
                hiddenText = "reset", // or any label you want
                onButtonClick = { onNavigateToFeed() },
                textValue = inputText,
                onTextChange = { inputText = it }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PostScreenPreview() {
    PostScreen(
        onNavigateToFeed = {} // no-op for preview
    )
}


@Composable
fun ThreeRowLayout(
    hiddenText: String = "",
    onButtonClick: () -> Unit,
    textValue: String,
    onTextChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Row 1: Hidden Text (left) and Button (right)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Hidden text (still takes space if you use alpha = 0f)
            Text(
                text = hiddenText,
                modifier = Modifier.alpha(0f) // Invisible but occupies space
            )

            Button(
                onClick = onButtonClick,
                modifier = Modifier
                    .widthIn(max = 75.dp),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Transparent
                ),
                elevation = null
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd // 👈 Aligns Canvas to the right edge
                ) {
                    Canvas(modifier = Modifier.size(28.dp)) {
                        val strokeColor = Color(0xFF8B8B8B)
                        val barWidth = size.width * 0.2f

                        val leftBarHeight = size.height * 0.7f
                        val middleBarHeight = size.height * 0.9f
                        val rightBarHeight = size.height * 0.7f

                        val leftX = size.width * 0.15f
                        val middleX = size.width * 0.5f - barWidth / 2
                        val rightX = size.width * 0.85f - barWidth

                        drawRoundRect(
                            color = strokeColor.copy(alpha = 0.8f),
                            topLeft = Offset(leftX, (size.height - leftBarHeight) / 2),
                            size = Size(barWidth, leftBarHeight),
                            cornerRadius = CornerRadius(barWidth, barWidth)
                        )
                        drawRoundRect(
                            color = strokeColor.copy(alpha = 0.8f),
                            topLeft = Offset(middleX, (size.height - middleBarHeight) / 2),
                            size = Size(barWidth, middleBarHeight),
                            cornerRadius = CornerRadius(barWidth, barWidth)
                        )
                        drawRoundRect(
                            color = strokeColor.copy(alpha = 0.8f),
                            topLeft = Offset(rightX, (size.height - rightBarHeight) / 2),
                            size = Size(barWidth, rightBarHeight),
                            cornerRadius = CornerRadius(barWidth, barWidth)
                        )
                    }
                }
            }


        }

        Spacer(modifier = Modifier.height(8.dp))

        // Row 2: Input Text Field
        BasicTextField(
            value = textValue,
            onValueChange = onTextChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp) // This adds space below the text (before the border)
                .drawBehind {
                    // Draw only the bottom border
                    val strokeWidth = 1.dp.toPx()
                    val y = size.height // Position at the bottom of the component
                    drawLine(
                        color = Color.Blue,
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
                        .padding(bottom = 8.dp) // Additional padding between text and border
                ) {
                    if (textValue.isEmpty()) {
                        Text(
                            "Thought",
                            fontSize = 24.sp,
                            color = Color.Gray,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    innerTextField()
                }
            }
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Row 3: Hidden Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.dp) // Or use alpha(0f) to keep space
        ) {
            // Hidden content
            Text(
                text = "Hidden row content",
                modifier = Modifier.alpha(0f) // Invisible but keeps height if needed
            )
        }
    }
}





/*
@Preview(showBackground = true)
@Composable
fun PostScreenPreview() {
    PostScreen(
        hiddenText = "reset",
        onButtonClick = {},
        textValue = "",
        onTextChange = {}
    )
}

/*@Preview(showBackground = true)
@Composable
fun ArtistCardPreview() {
    MaterialTheme {
        ArtistCard()
    }
}*/*/