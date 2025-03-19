package com.smorzhok.libraryapp.presentation.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class MyScreen(val route: String, val title: String, val icon: ImageVector) {
    data object Search : MyScreen("search", "Поиск", Icons.Default.Search)
    data object Favorites : MyScreen("favorites", "Избранное", Icons.Default.Favorite)
}