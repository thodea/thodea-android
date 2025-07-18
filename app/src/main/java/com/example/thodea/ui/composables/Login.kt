package com.example.thodea.ui.composables

import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import androidx.core.net.toUri
import kotlinx.coroutines.delay

@Composable
fun GreenBorderCheckbox() {
    var checked by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .size(30.dp)
            .padding(end = 4.dp)
            .background(Color.Transparent, shape = RoundedCornerShape(4.dp))
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = { checked = it },
            colors = CheckboxDefaults.colors(
                checkedColor = Color.Transparent,
                uncheckedColor = Color.Transparent,
                checkmarkColor = Color.Green
            ),
            modifier = Modifier
                .background(Color.Transparent)
                .border(
                    BorderStroke(2.dp, Color.Transparent),
                    shape = RoundedCornerShape(4.dp)
                )
        )
    }
}

@Composable
//fun LoginScreen(onLoginSuccess: () -> Unit) {
fun LoginScreen() {
    // State to control whether the email confirmation message is shown
       var emailSent by remember { mutableStateOf(false) }
    // State to hold the email input value
    var email by remember { mutableStateOf("") }

    fun onUserLogin() {
        // Example dummy check: treat "test@example.com" as correct.
        emailSent = true
            //onLoginSuccess()
    }

    Scaffold(
        containerColor = Color(0xFF111827) // Dark background (#111827) from original HTML
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Apply Scaffold's padding to avoid overlap with system bars
                .systemBarsPadding() // Ensures content adapts to system bars
        ) {
            // Conditionally display either the login form or the email sent confirmation
            if (!emailSent) {
                // Display the LoginForm component
                FirstRowLoginLayout(
                    email = email,
                    onEmailChange = { email = it },
                    onSignInGoogle = {  },
                    onSignInMicrosoft = {  },
                    onSignInYahoo = {  },
                    onUserLogin = { onUserLogin() }, // ✅ works too
                )
            } else {
                // Display the email confirmation message
                //EmailSentConfirmation()
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    GreenBorderCheckbox()
                    Text(
                        text = "Check email to log in",
                        color = Color(229 ,231, 235),
                        fontSize = 24.sp // text-2xl (adjust with breakpoints if needed)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    //LoginScreen(onLoginSuccess = {})
    LoginScreen()
}

@Composable
fun FirstRowLoginLayout(
    email: String,
    onEmailChange: (String) -> Unit, // This lambda will be called to update email in LoginScreen
    onSignInGoogle: () -> Unit,
    onSignInMicrosoft: () -> Unit,
    onSignInYahoo: () -> Unit,
    onUserLogin: () -> Unit, // This lambda will be called to update emailSent in LoginScreen
) {

    // This Column represents the main container for the login form elements.
    // The padding from the original Next.js code is applied here.
    Column(
            modifier = Modifier
                .fillMaxSize() // You can use fillMaxSize() instead of fillMaxWidth() + fillMaxHeight()
                .padding(start = 16.dp, end = 16.dp, top = 8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
        // The original Next.js code had a Row containing the "Log In" text and then the LoginForm.
        // Since LoginForm already includes the "Log In" text, we need to decide where it should be.
        // If you want a separate "Log In" header *outside* the LoginForm's styled box,
        // you would place it here. However, to match the original Next.js where "Log In"
        // is *inside* the login box, we simply call LoginForm directly.

        // Removed the redundant "Log In" text block from here:
        // Row(
        //     modifier = Modifier.padding(bottom = 4.dp).alpha(0.9f).border(
        //         width = 1.dp,
        //         color = Color(31, 41, 55),
        //         shape = RoundedCornerShape(8.dp)
        //     ).fillMaxWidth(),
        //     horizontalArrangement = Arrangement.Center,
        // ) {
        //     Text(
        //         text = "Log In",
        //         fontSize = 28.sp,
        //         fontWeight = FontWeight.SemiBold,
        //         color = Color.White.copy(alpha = 0.8f),
        //     )
        // }
        // Spacer(modifier = Modifier.width(8.dp)) // This spacer is also not needed here

        // Now, the LoginForm is called directly. It contains its own "Log In" text and styling.
        // All event handling and state updates are passed down from LoginScreen through this component.
        LoginForm(
            email = email,
            onEmailChange = onEmailChange,
            onSignInGoogle = onSignInGoogle,
            onSignInMicrosoft = onSignInMicrosoft,
            onSignInYahoo = onSignInYahoo,
            onUserLogin = onUserLogin, // This will trigger the emailSent state update in LoginScreen,
        )
    }
}




@Composable
fun LoginForm(
    email: String,
    onEmailChange: (String) -> Unit,
    onSignInGoogle: () -> Unit,
    onSignInMicrosoft: () -> Unit,
    onSignInYahoo: () -> Unit,
    onUserLogin: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(align = Alignment.CenterHorizontally)
            .padding(bottom = 5.dp), // mx-auto equivalent
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Login Box
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
                .widthIn(max = 450.dp) // max-w-[450px]
                .border(
                    width = 0.5.dp,
                    color = Color(58, 58, 58, 255), // dark:border-gray-600
                    shape = RoundedCornerShape(8.dp)
                )
                .shadow(
                    elevation = 8.dp, // shadow-lg
                    shape = RoundedCornerShape(8.dp),
                    ambientColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                    spotColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                )
                .background(Color.Transparent) // Default background for the card
                .padding(top = 4.dp, bottom = 12.dp)
        ) {
            Text(
                text = "Log In",
                fontSize = 22.sp, // text-2xl
                color = Color(194, 194, 194, 255), // text-blue-200
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 4.dp) // p-2 pb-4
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceAround // justify-between
            ) {
                // Google Image
                AsyncImage(
                    model = "https://cdn.nikpevnev.com/assets/store/design/google.webp",
                    contentDescription = "Yeti",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(70.dp)
                        .padding(4.dp)
                        .clickable(onClick = onSignInGoogle)
                )


                // Microsoft Image
                AsyncImage(
                    model = "https://cdn.nikpevnev.com/assets/store/design/microsoft.webp",
                    contentDescription = "Yeti",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(70.dp)
                        .padding(4.dp)
                        .clickable(onClick = onSignInMicrosoft)
                )


                // Yahoo Image
                AsyncImage(
                    model = "https://cdn.nikpevnev.com/assets/store/design/yahoo.webp",
                    contentDescription = "Yeti",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(70.dp)
                        .padding(4.dp)
                        .clickable(onClick = onSignInYahoo)
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            // "Or" Divider
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp,vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    color = Color(17, 93, 180)
                )
                Text(
                    text = "Or",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(horizontal = 8.dp),
                    color = Color(17, 93, 180)
                )
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    color = Color(17, 93, 180)
                )
            }

            // Email Input
            /*OutlinedTextField(
                value = email,
                onValueChange = onEmailChange,
                label = { Text("Email") },
                placeholder = { Text("Email", color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
                    unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedBorderColor = Color.Blue, // dark:border-blue-900
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp) // approximate h-[40px] with label
                    .padding(horizontal = 16.dp, vertical = 4.dp) // m-4
            )*/

            var showError by remember { mutableStateOf(false) }

            Spacer(modifier = Modifier.height(18.dp))

            Row(modifier = Modifier.height(40.dp)) {
                if (!showError) {
                    BasicTextField(
                        maxLines = 1,
                        value = email,
                        onValueChange = {
                            val sanitized = it
                                .replace("\n", "")
                                .filter { char ->
                                    char.isLetterOrDigit() || char in setOf('.', '_', '%', '+', '-', '@')
                                }

                            if (sanitized.length <= 150) {
                                onEmailChange(sanitized)
                            }
                        },
                        singleLine = true,
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 16.dp, vertical = 4.dp)
                            .height(50.dp)
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
                                if (email.isEmpty()) {
                                    Text(
                                        text = "Email",
                                        fontSize = 24.sp,
                                        color = Color.Gray
                                    )
                                }
                                innerTextField()
                            }
                        }
                    )
                } else {
                    Text(
                        text = "Enter valid email",
                        fontSize = 24.sp,
                        color = Color(255,131,131),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 4.dp)
                            .height(50.dp)
                            .drawBehind {
                                val strokeWidth = 1.dp.toPx()
                                val y = size.height
                                drawLine(
                                    color = Color(30, 58, 138),
                                    start = Offset(0f, y),
                                    end = Offset(size.width, y),
                                    strokeWidth = strokeWidth
                                )
                            }
                    )
                }

            }
            var triggerError by remember { mutableStateOf(false) }
            // Email regex for validation

            // Handle error reset after 2 seconds
            LaunchedEffect(triggerError) { // Use triggerError as key to re-launch effect
                if (triggerError) {
                    delay(2000)
                    showError = false
                    triggerError = false
                }
            }


            Spacer(modifier = Modifier.height(16.dp))
            // Enter Button
            val emailRegex = Regex(
                "^(?!.*\\.\\.)[a-zA-Z0-9](?:[a-zA-Z0-9._%+-]{0,63}[a-zA-Z0-9])?@[a-zA-Z0-9](?:[a-zA-Z0-9.-]{0,253}[a-zA-Z0-9])?\\.[a-zA-Z]{2,}\$"
            )
            Button(
                onClick = { // This is the lambda block expected by onClick
                    if (!emailRegex.matches(email)) {
                        showError = true
                        triggerError = true // Set state to trigger LaunchedEffect
                    } else {
                        onUserLogin() // Call the function
                    }
                },
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
                        text = "Enter",
                        fontSize = 22.sp,
                        color = Color.White
                    )
                }
            }
        }


        // Function to handle the click and open the URL
        val context = LocalContext.current

        // Terms and Privacy Text
        Row(
            modifier = Modifier
                .fillMaxWidth() // ✅ Make row take full width
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center, // ✅ Center content horizontally
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) { // ✅ Center Column content
                Text(
                    text = "By entering you agree to ",
                    fontSize = 18.sp, // ✅ Bigger text
                    color = Color.Gray // ✅ Gray text
                )
                Row {
                    Text(
                        text = "Terms of Use",
                        fontSize = 18.sp, // ✅ Match size
                        fontWeight = FontWeight.Medium,
                        color = Color(59, 130, 246), // ✅ Base color is gray (blue styles override)
                        modifier = Modifier.padding(top = 4.dp).clickable {
                            val customTabsIntent = CustomTabsIntent.Builder().build()
                            customTabsIntent.launchUrl(context,
                                "https://www.thodea.com/policy/terms".toUri())
                        }
                    )
                    Text(
                        text = " and ",
                        fontSize = 18.sp, // ✅ Match size
                        color = Color.Gray, // ✅ Base color is gray (blue styles override)
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    Text(
                        text = "Privacy Policy",
                        fontSize = 18.sp, // ✅ Match size
                        fontWeight = FontWeight.Medium,
                        color = Color(59, 130, 246), // ✅ Base color is gray (blue styles override)
                        modifier = Modifier.padding(top = 4.dp).clickable {
                            val customTabsIntent = CustomTabsIntent.Builder().build()
                            customTabsIntent.launchUrl(context,
                                "https://www.thodea.com/policy/privacy".toUri())
                        }
                    )
                }

            }
        }


    }
}