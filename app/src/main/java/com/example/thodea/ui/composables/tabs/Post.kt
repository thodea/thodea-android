package com.example.thodea.ui.composables.tabs


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.thodea.R
import com.example.thodea.ui.theme.typography


@Composable
fun PostScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF111827)), // dark background (#111827)
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.post),
            style = typography.titleLarge,
            color = Color(0xFF6B7280) // text color (#6B7280)
        )
    }
}


/*class PostFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Use ComposeView to define the entire UI with Jetpack Compose
        return ComposeView(requireContext()).apply {
            setContent {
                var inputText by remember { mutableStateOf("") }

                ThreeRowLayout(
                    hiddenText = "Invisible Left",
                    onButtonClick = { Log.d("PostFragment", "Button clicked") },
                    textValue = inputText,
                    onTextChange = { inputText = it }
                )
            }
        }
    }
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
                    .widthIn(max = 75.dp)
                    //.border(1.dp, Color.Gray, shape = RoundedCornerShape(6.dp))
                ,
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent,
                    contentColor = Color.Transparent
                ),
                elevation = null
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd // 👈 Aligns Canvas to the right edge
                ) {
                    Canvas(modifier = Modifier.size(24.dp)) {
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
        TextField(
            value = textValue,
            onValueChange = onTextChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = "Thought",
                    fontSize = 20.sp,
                    color = Color.Gray
                )
            },
            textStyle = TextStyle(
                fontSize = 20.sp, // Adjust size here
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color(229, 231, 235),
                focusedBorderColor = Color.Blue,
                unfocusedBorderColor = Color.Blue
            )
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



@Composable
fun PostScreen(
    hiddenText: String = "",
    textValue: String,
    onTextChange: (String) -> Unit,
    onButtonClick: () -> Unit
) {
    Scaffold(
        backgroundColor = Color.Transparent
    ) { paddingValues -> // consume the system bars and topBar padding
        ThreeRowLayout(
            hiddenText = hiddenText,
            onButtonClick = onButtonClick,
            textValue = textValue,
            onTextChange = onTextChange,
        ).let {
            // Apply the scaffold's padding to the whole layout
            Box(modifier = Modifier.padding(paddingValues)) {
                //it
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PostScreenPreview() {
    MaterialTheme {
        PostScreen(
            hiddenText = "reset",
            onButtonClick = {},
            textValue = "",
            onTextChange = {}
        )
    }
}

/*@Preview(showBackground = true)
@Composable
fun ArtistCardPreview() {
    MaterialTheme {
        ArtistCard()
    }
}*/*/