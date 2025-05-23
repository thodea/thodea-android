package com.example.thodea

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
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
        setContent {
            BottomTabNavigationJetpackComposeTheme {
                val navController = rememberNavController()
                Surface(modifier = Modifier.fillMaxSize(),
                    color = Color.Transparent ) {
                    MainScreen(navController = navController)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val navController = rememberNavController()
    BottomTabNavigationJetpackComposeTheme {
        Surface(modifier = Modifier.fillMaxSize(),
            color = Color.Transparent ) {
            MainScreen(navController = navController)
        }
    }
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