package com.example.thodea.ui.composables.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
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

val profileInfo = ProfileInfo(followers = 1200, following = 256)


@Composable
fun ProfileScreen() {
    var selectedTab by remember { mutableStateOf("thoughts") }

    Scaffold(
        containerColor = Color(0xFF111827) // Dark background (#111827)
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            FirstRowLayout(
                selectedTab = selectedTab,
                onSelectTab = { tab -> selectedTab = tab },
                profileInfo = profileInfo,
                onFollowersClick = {
                    // Handle followers click
                    println("Followers clicked: ${profileInfo.followers}")
                },
                onFollowingClick = {
                    // Handle following click
                    println("Following clicked: ${profileInfo.following}")
                })
        }
    }
}



@Composable
fun FirstRowLayout(
    selectedTab: String,
    onSelectTab: (String) -> Unit,
    profileInfo: ProfileInfo?,
    onFollowersClick: () -> Unit,
    onFollowingClick: () -> Unit
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

        Row(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .height(100.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(0.6f) // Left column takes 50% width
            ) {
                Row {
                    Box(
                        modifier = Modifier
                            .width(100.dp) // Take remaining space in the Column
                            .aspectRatio(1f) // Make it square
                            .clip(RoundedCornerShape(12.dp)) // Rounded corners
                            .background(Color(0x2260A5FA)) // Tailwind sky-400 style blue
                    )
                }
            }

            Column(
                modifier = Modifier
                    .weight(1f) // Right column takes 50% width
            ) {
                ProfileStatRow(
                    count = profileInfo?.followers,
                    label = if ((profileInfo?.followers ?: 0) == 1) "follower" else "followers",
                    enabled = (profileInfo?.followers ?: -1) > 0,
                    onClick = onFollowersClick
                )

                ProfileStatRow(
                    count = profileInfo?.following,
                    label = "following",
                    enabled = (profileInfo?.following ?: -1) > 0,
                    onClick = onFollowingClick
                )

                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Row(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .height(100.dp)
        ) {
            ProfileNavScreen(
                selectedTab = selectedTab,
                onSelectTab = onSelectTab)
        }

        // Display section based on selected tab
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            contentAlignment = Alignment.Center
        ) {
            val (bgColor, label) = when (selectedTab) {
                "thoughts" -> Pair(Color(0xFF143FAB), "Thoughts content")     // Blue-600
                "loved" -> Pair(Color(0xFF3A0E77), "Loved content")           // Pink-500
                "mentioned" -> Pair(Color(0xFF094430), "Mentioned content")   // Emerald-500
                else -> Pair(Color.Gray, "")
            }

            Box(
                modifier = Modifier
                    .background(bgColor, shape = RoundedCornerShape(16.dp))
                    .padding(horizontal = 24.dp, vertical = 12.dp)
            ) {
                Text(
                    text = label,
                    fontSize = 18.sp,
                    color = Color.White
                )
            }
        }
    }
}



@Composable
fun ProfileStatRow(
    count: Int?,
    label: String,
    enabled: Boolean,
    onClick: () -> Unit
) {
    val windowInfo = LocalWindowInfo.current
    val density = LocalDensity.current

    // Convert containerSize width (in px) to dp
    val screenWidthDp = remember(windowInfo) {
        with(density) {
            windowInfo.containerSize.width.toDp()
        }
    }

    // Calculate width based on actual window size
    val dividerWidth = remember(screenWidthDp) {
        when {
            screenWidthDp < 360.dp -> 25.dp // Small screen
            screenWidthDp < 480.dp -> 50.dp // Medium screen
            else -> 100.dp // Large screen
        }
    }
    val interactionModifier = if (enabled) {
        Modifier.clickable { onClick() }
    } else {
        Modifier
    }

    Row(
        modifier = interactionModifier
            .fillMaxWidth()
            .height(48.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // Text content
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f).padding(end = 8.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                fontSize = 18.sp,
                text = count?.let { formatNumber(it) } ?: "",
                style = MaterialTheme.typography.bodyLarge,
                color = Color(229, 231, 235)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                fontSize = 18.sp,
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                color = Color(229, 231, 235)
            )
        }

        // Divider with dot
        Box(
            modifier = Modifier
                .height(48.dp) // Match row height
                .widthIn(min = 25.dp, max = 100.dp) // Responsive width range
                .width(dividerWidth) // Use calculated width
        ) {
            HorizontalDivider(
                thickness = 2.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .align(Alignment.Center),
                color = Color(2,132, 199)
            )

            DotIndicator(
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }
    }
}

@Composable
fun DotIndicator(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(top = 1.dp)
            .size(8.dp)
            .background(
                color = Color(7, 89, 133),
                shape = RectangleShape
            )
    )
}
// Sample number formatter (can be replaced with real logic)
fun formatNumber(num: Int): String {
    return when {
        num >= 1_000_000_000 -> "${(num / 1_000_000_000).toFloat().let { if (it % 1 == 0f) it.toInt() else it }}b"
        num >= 1_000_000 -> "${(num / 1_000_000).toFloat().let { if (it % 1 == 0f) it.toInt() else it }}m"
        num >= 1_000 -> "${(num / 1_000).toFloat().let { if (it % 1 == 0f) it.toInt() else it }}k"
        else -> num.toString()
    }
}

@Composable
fun ProfileNavScreen(selectedTab: String,
                     onSelectTab: (String) -> Unit,) {
    // State for currently selected tab

    // Example profile info
    val profileInfo = mapOf("thoughts" to 123, "loved" to 45, "mentioned" to 10)

    // Optional: Example flag for top margin
    val bioInfo = true

    // Pass all props into ProfileTabs
    ProfileTabs(
        bioInfo = bioInfo,
        profileInfo = profileInfo,
        selectedTab = selectedTab,
        onSelectTab = onSelectTab
    )
}

@Composable
fun ProfileTabs(
    bioInfo: Boolean,
    profileInfo: Map<String, Any>,
    selectedTab: String,
    onSelectTab: (String) -> Unit
) {
    val modifier = if (bioInfo) Modifier.padding(top = 8.dp) else Modifier.padding(top = 16.dp)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(max = 50.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TabItem(
            modifier = Modifier.weight(1f),
            label = "thoughts",
            count = profileInfo["thoughts"]?.toString(),
            isSelected = selectedTab == "thoughts",
            onClick = { onSelectTab("thoughts") }
        )
        TabItem(
            modifier = Modifier.weight(1f),
            label = "loved",
            isSelected = selectedTab == "loved",
            onClick = { onSelectTab("loved") }
        )
        TabItem(
            modifier = Modifier.weight(1f),
            label = "mentions",
            isSelected = selectedTab == "mentioned",
            onClick = { onSelectTab("mentioned") }
        )
    }
}

@Composable
fun TabItem(
    modifier: Modifier = Modifier,
    label: String,
    count: String? = null,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    Box(
        modifier = modifier
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (count != null) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = label,
                        fontSize = 18.sp,
                        color = Color(229, 231, 235)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = count.let { formatNumber(it.toInt()) },
                        fontSize = 18.sp,
                        color = Color(229, 231, 235)
                    )
                }
            } else {
                Text(
                    text = label,
                    fontSize = 18.sp,
                    color = Color(229, 231, 235)
                )
            }
        }

        // Bottom border (2dp thick)
        if (isSelected) {
            HorizontalDivider(
                thickness = 2.dp,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(2.dp),
                color = Color(7, 89, 133) // Sky-400
            )
        }
    }
}

// Data class for profile info
data class ProfileInfo(
    val followers: Int,
    val following: Int
)