package com.example.thodea.ui.composables.tabs

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
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thodea.R

/**
 * Composable function that represents the profile screen of the application.
 */

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}

@Composable
fun ProfileScreen() {
    Scaffold(
        containerColor = Color(0xFF111827) // Dark background (#111827)
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            FirstRowLayout(
            )
        }
    }
}



@Composable
fun FirstRowLayout(
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        // Row 1: Hidden Text (left) and Button (right)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Hidden text (still takes space if you use alpha = 0f)
            Row(
                verticalAlignment = Alignment.CenterVertically // aligns circle with the text's middle
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable(onClick = { })
                ) {
                    // SVG icon - you'll need to add the vector asset to your project
                    Icon(
                        painter = painterResource(id = R.drawable.ic_settings), // Replace with your actual SVG
                        contentDescription = "Image icon",
                        tint = Color(0xFF9ca3af), // Adjust color as needed
                        modifier = Modifier
                            .size(28.dp)
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically // aligns circle with the text's middle
            ) {
                Text(
                    text = "username",
                    color = Color(0xFFE5E7EB), // RGB(229, 231, 235) in hex
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable(onClick = { })
            ) {
                // SVG icon - you'll need to add the vector asset to your project
                Icon(
                    painter = painterResource(id = R.drawable.ic_mail), // Replace with your actual SVG
                    contentDescription = "Image icon",
                    tint = Color(0xFF9ca3af), // Adjust color as needed
                    modifier = Modifier
                        .size(28.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}


// Row 2: Input Text Field