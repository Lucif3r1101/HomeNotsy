package com.rishav.notsy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rishav.notsy.view.screen.AddNoteScreen
import com.rishav.notsy.view.screen.ListNoteScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = "list") {
                        composable("list") { ListNoteScreen(navController) }
                        composable("add") { AddNoteScreen(navController) }
                    }
                }
            }
        }
    }
}
