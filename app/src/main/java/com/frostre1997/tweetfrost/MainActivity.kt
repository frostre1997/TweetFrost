package com.frostre1997.tweetfrost

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import com.yourpackage.tweetfrost.data.local.TokenManager
import com.yourpackage.tweetfrost.ui.login.LoginScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tokenManager = TokenManager(this)

        setContent {
            // If logged in, navigate to MainScreen (you'll build that later)
            // Otherwise show LoginScreen
            if (tokenManager.isLoggedIn()) {
                // Replace with your main timeline screen
                // MainScreen()
                Text("Logged in – show timeline")
            } else {
                LoginScreen()
            }
        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tokenManager = TokenManager(this)
        
        // Create ApiService once
        val apiService = RetrofitClient.getApiService(tokenManager)

        setContent {
            if (tokenManager.isLoggedIn()) {
                // Pass apiService to HomeScreen
                HomeScreen(apiService = apiService)
            } else {
                LoginScreen()
            }
        }
    }
}
