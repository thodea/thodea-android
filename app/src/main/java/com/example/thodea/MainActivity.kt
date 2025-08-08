package com.example.thodea

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.thodea.ui.composables.GoogleAuthClient
import com.example.thodea.ui.composables.MainScreen
import com.example.thodea.ui.theme.BottomTabNavigationJetpackComposeTheme

class MainActivity : ComponentActivity() {
    /**
     * Called when the activity is starting. This is where most initialization should go:
     * calling `setContentView`, instantiating UI components, and binding data.
     *
     * @param savedInstanceState The previously saved instance state, if any.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val googleAuthClient = GoogleAuthClient(applicationContext)
        setContent {
            BottomTabNavigationJetpackComposeTheme {
                val navController = rememberNavController()
                val isSignedIn by rememberSaveable {
                    mutableStateOf(googleAuthClient.isSignedIn())
                }

                Surface(modifier = Modifier.fillMaxSize(),
                    color = Color.Transparent ) {
                    MainScreen(
                        navController = navController,
                        isSignedIn = isSignedIn,
                        googleAuthClient = googleAuthClient,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val navController = rememberNavController()
    val fakeAuthClient = FakeAuthClient()
    BottomTabNavigationJetpackComposeTheme {
        Surface(modifier = Modifier.fillMaxSize(),
            color = Color.Transparent ) {
            MainScreen(
                navController = navController,
                isSignedIn = false,
                googleAuthClient = fakeAuthClient,
            )
        }
    }
}

interface AuthClient {
    suspend fun signIn(): Boolean
    suspend fun signOut()
    fun isSignedIn(): Boolean
}

class FakeAuthClient : AuthClient {
    override fun isSignedIn(): Boolean = false
    override suspend fun signIn(): Boolean = true
    override suspend fun signOut() {}
}


/*import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.thodea.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide() // ← hides the top bar
        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: BottomNavigationView = binding.navView

        // Remove extra padding from the navigation bar
        navView.setOnApplyWindowInsetsListener(null)
        navView.setPadding(0,0,0,0)

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        /*val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_post, R.id.navigation_feed, R.id.navigation_search
            )
        )*/
        //setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

}*/