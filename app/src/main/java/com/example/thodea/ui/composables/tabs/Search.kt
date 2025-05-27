package com.example.thodea.ui.composables.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Composable function that represents the search screen of the application.
 */

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen()
}

@Composable
fun SearchScreen() {
    var inputText by remember { mutableStateOf("") }

    Scaffold(
        containerColor = Color(0xFF111827) // Dark background (#111827)
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            FirstRowLayout(
                onButtonClick = { },
                textValue = inputText,
                onTextChange = { inputText = it }
            )
        }
    }
}


@Composable
fun FirstRowLayout(
    onButtonClick: () -> Unit,
    textValue: String,
    onTextChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f) // take available space
            ) {
                BasicTextField(
                    maxLines = 1,
                    value = textValue,
                    onValueChange = onTextChange,
                    singleLine = true,
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = 4.dp)
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
                            if (textValue.isEmpty()) {
                                Text(
                                    "Search",
                                    fontSize = 24.sp,
                                    color = Color.Gray
                                )
                            }
                            innerTextField()
                        }
                    }
                )

                if (textValue.isNotEmpty()) {
                    IconButton(
                        onClick = { onTextChange("") },
                        modifier = Modifier
                            .padding(end = 6.dp, start = 6.dp)
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
            Spacer(modifier = Modifier.width(8.dp))

            // Search Icon
            Box(
                modifier = Modifier
                    .size(36.dp) // give enough space for touch target
                    .background(color = Color(7, 89, 133), shape = RoundedCornerShape(8.dp))
                    .clickable { onButtonClick() }
                    .padding(4.dp), // inner padding for the icon itself
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = Color(0xFFd1d5db),
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        // Selection tabs
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            var selectedItem by remember { mutableStateOf("Thoughts") }
            var selectedTime by remember { mutableStateOf("Today") }

            TabSelector(
                selectedItem = selectedItem,
                selectedTime = selectedTime,
                onItemClick = { item -> selectedItem = item },
                onTimeClick = { time -> selectedTime = time }
            )
        }
    }
}


@Composable
fun TabSelector(
    selectedItem: String,
    selectedTime: String,
    onItemClick: (String) -> Unit,
    onTimeClick: (String) -> Unit
) {
    Column {
        // Main tabs (Thoughts/Users)
        Row(
            modifier = Modifier
                .padding(top = 4.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TabItem(
                text = "Thoughts",
                isSelected = selectedItem == "Thoughts",
                onClick = { onItemClick("Thoughts") }
            )

            TabItem(
                text = "Users",
                isSelected = selectedItem == "Users",
                onClick = { onItemClick("Users") },
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        // Time tabs (only shown when Thoughts is selected)
        if (selectedItem == "Thoughts") {
            Row(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TimeTabItem(
                    text = "Today",
                    isSelected = selectedTime == "Today",
                    onClick = { onTimeClick("Today") }
                )

                TimeTabItem(
                    text = "Week",
                    isSelected = selectedTime == "Week",
                    onClick = { onTimeClick("Week") },
                    modifier = Modifier.padding(start = 16.dp)
                )

                TimeTabItem(
                    text = "All",
                    isSelected = selectedTime == "All",
                    onClick = { onTimeClick("All") },
                    modifier = Modifier.padding(start = 16.dp)
                )

                TimeTabItem(
                    text = "Recent",
                    isSelected = selectedTime == "Recent",
                    onClick = { onTimeClick("Recent") },
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}

@Composable
private fun TabItem(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (isSelected) {
        Color(0xFF0E416B)  // dark:bg-sky-800 / bg-sky-50
    } else {
        Color.Transparent
    }

    val borderColor = if (isSelected) {
        Color(0xFF1D4E78) // dark:border-sky-700 / border-sky-100
    } else {
        Color.Transparent
    }

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(backgroundColor)
            .border(1.dp, borderColor, RoundedCornerShape(4.dp))
            .clickable { onClick() }
            .padding(horizontal = 8.dp, vertical = 1.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            fontSize = 21.sp,
            color = Color(229, 231, 235)
        )
    }
}

@Composable
private fun TimeTabItem(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (isSelected) {
         Color(0xFF0E416B) // dark:bg-sky-800 / bg-sky-50
    } else {
        Color.Transparent
    }

    val borderColor = if (isSelected) {
        Color(0xFF1D4E78) // dark:border-sky-700 / border-sky-100
    } else {
        Color.Transparent
    }

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(backgroundColor)
            .border(1.dp, borderColor, RoundedCornerShape(4.dp))
            .clickable { onClick() }
            .padding(horizontal = 8.dp, vertical = 1.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            fontSize = 21.sp,
            color = Color(229, 231, 235)
        )
    }
}

