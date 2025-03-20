package com.smorzhok.libraryapp.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.smorzhok.libraryapp.presentation.navigation.BottomNavigationBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) {
        NavHost(
            navController = navController,
            startDestination = MyScreen.Search.route,
            modifier = Modifier.fillMaxSize()
        ) {
            composable(MyScreen.Search.route) {
                SearchScreen(navController)
            }
            composable(MyScreen.Favorites.route) {
                FavoritesScreen(navController)
            }
            composable("book_detail/{bookId}") { backStackEntry ->
                val bookIdString = backStackEntry.arguments?.getString("bookId") ?: return@composable
                val bookId = bookIdString.toIntOrNull() ?: return@composable
                BookDetailScreen(bookId, navController)
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}