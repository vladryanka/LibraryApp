package com.smorzhok.libraryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.smorzhok.libraryapp.data.remote.RepositoryProvider
import com.smorzhok.libraryapp.domain.LibraryRepository
import com.smorzhok.libraryapp.presentation.screens.MainScreen
import com.smorzhok.libraryapp.ui.theme.LibraryAppTheme

class MainActivity : ComponentActivity() {
    private lateinit var repository: LibraryRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repository = RepositoryProvider.getRepository()
        enableEdgeToEdge()
        setContent {
            MainScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LibraryAppTheme {
        //MainScreen("Android")
    }
}