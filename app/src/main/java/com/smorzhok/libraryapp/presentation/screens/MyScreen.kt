package com.smorzhok.libraryapp.presentation.screens

import com.smorzhok.libraryapp.R

sealed class MyScreen(val route: String, val title: String, val icon: Int) {
    data object Search : MyScreen(
        "search", "Поиск",
        R.drawable.search_blue
    )

    data object Favorites : MyScreen("favorites", "Избранное", R.drawable.favorite_icon)
}