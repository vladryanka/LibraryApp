package com.smorzhok.libraryapp.presentation.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.smorzhok.libraryapp.presentation.navigation.BottomNavigationBar

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = MyScreen.Search.route,
            modifier = Modifier.padding(paddingValues)
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