package com.wordlex.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.wordlex.app.ui.auth.AuthScreen
import com.wordlex.app.ui.home.HomeScreen
import com.wordlex.app.ui.theme.WordlexTheme
import com.wordlex.app.ui.word.AddWordScreen
import com.wordlex.app.ui.word.WordListScreen
import com.wordlex.app.ui.quiz.QuizScreen
import com.wordlex.app.ui.settings.SettingsScreen
import com.wordlex.app.ui.report.ReportScreen
import com.wordlex.app.ui.wordle.WordleScreen
import com.wordlex.app.ui.wordchain.WordChainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WordlexTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "auth",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("auth") {
                            AuthScreen(
                                onLoginSuccess = { userId ->
                                    navController.navigate("home/$userId") {
                                        popUpTo("auth") { inclusive = true }
                                    }
                                }
                            )
                        }

                        composable(
                            route = "home/{userId}",
                            arguments = listOf(navArgument("userId") { type = NavType.LongType })
                        ) { backStackEntry ->
                            val userId = backStackEntry.arguments?.getLong("userId") ?: 0L
                            HomeScreen(
                                userId = userId,
                                onAddWordClick = {
                                    navController.navigate("add_word/$userId")
                                },
                                onWordListClick = {
                                    navController.navigate("word_list/$userId")
                                },
                                onQuizClick = {
                                    navController.navigate("quiz/$userId")
                                },
                                onSettingsClick = {
                                    navController.navigate("settings/$userId")
                                },
                                onReportClick = {
                                    navController.navigate("report/$userId")
                                },
                                onWordleClick = {
                                    navController.navigate("wordle/$userId")
                                },
                                onWordChainClick = {
                                    navController.navigate("wordchain/$userId")
                                }
                            )
                        }

                        composable(
                            route = "add_word/{userId}",
                            arguments = listOf(navArgument("userId") { type = NavType.LongType })
                        ) { backStackEntry ->
                            val userId = backStackEntry.arguments?.getLong("userId") ?: 0L
                            AddWordScreen(
                                userId = userId,
                                onBackClick = { navController.popBackStack() },
                                onWordSaved = {
                                    navController.navigate("word_list/$userId") {
                                        popUpTo("home/$userId")
                                    }
                                }
                            )
                        }

                        composable(
                            route = "word_list/{userId}",
                            arguments = listOf(navArgument("userId") { type = NavType.LongType })
                        ) { backStackEntry ->
                            val userId = backStackEntry.arguments?.getLong("userId") ?: 0L
                            WordListScreen(
                                userId = userId,
                                onBackClick = { navController.popBackStack() },
                                onAddWordClick = {
                                    navController.navigate("add_word/$userId")
                                }
                            )
                        }

                        composable(
                            route = "quiz/{userId}",
                            arguments = listOf(navArgument("userId") { type = NavType.LongType })
                        ) { backStackEntry ->
                            val userId = backStackEntry.arguments?.getLong("userId") ?: 0L
                            QuizScreen(
                                userId = userId,
                                onBackClick = { navController.popBackStack() },
                                onQuizFinished = { navController.popBackStack() }
                            )
                        }

                        composable(
                            route = "settings/{userId}",
                            arguments = listOf(navArgument("userId") { type = NavType.LongType })
                        ) { backStackEntry ->
                            val userId = backStackEntry.arguments?.getLong("userId") ?: 0L
                            SettingsScreen(
                                userId = userId,
                                onBackClick = { navController.popBackStack() },
                                onLogout = {
                                    navController.navigate("auth") {
                                        popUpTo(0) { inclusive = true }
                                    }
                                }
                            )
                        }

                        composable(
                            route = "report/{userId}",
                            arguments = listOf(navArgument("userId") { type = NavType.LongType })
                        ) { backStackEntry ->
                            val userId = backStackEntry.arguments?.getLong("userId") ?: 0L
                            ReportScreen(
                                userId = userId,
                                onBackClick = { navController.popBackStack() }
                            )
                        }

                        composable(
                            route = "wordle/{userId}",
                            arguments = listOf(navArgument("userId") { type = NavType.LongType })
                        ) { backStackEntry ->
                            val userId = backStackEntry.arguments?.getLong("userId") ?: 0L
                            WordleScreen(
                                userId = userId,
                                onBackClick = { navController.popBackStack() }
                            )
                        }

                        composable(
                            route = "wordchain/{userId}",
                            arguments = listOf(navArgument("userId") { type = NavType.LongType })
                        ) { backStackEntry ->
                            val userId = backStackEntry.arguments?.getLong("userId") ?: 0L
                            WordChainScreen(
                                userId = userId,
                                onBackClick = { navController.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}