package com.smorzhok.libraryapp.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun FavoritesScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text("Экран избранного", modifier = Modifier.align(Alignment.CenterHorizontally))
    }
}