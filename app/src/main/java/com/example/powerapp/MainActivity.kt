package com.example.powerapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.powerapp.ui.CurrentConditions
import com.example.powerapp.ui.ForecastScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate() called")
        setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = "CurrentConditions") {
                composable("CurrentConditions") {
                    CurrentConditions {
                        navController.navigate("Forecast")
                    }
                }

                composable("Forecast") {
                    ForecastScreen()
                }
            }
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}