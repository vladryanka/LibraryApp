package com.smorzhok.libraryapp.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.smorzhok.libraryapp.R

@Composable
fun BookDetailScreen(bookId: Int, navController: NavController, detailViewModel: DetailViewModel = viewModel()) {

    LaunchedEffect(bookId) {
        detailViewModel.getBookById(bookId)
    }

    val book by detailViewModel.book.observeAsState()

    if (book == null) {
        Text(text = "Загрузка...", modifier = Modifier.padding(16.dp))
        return
    }

    val paint = if (book!!.imageLinks?.thumbnail == null)
        painterResource(R.drawable.book_placeholder)
    else
        rememberAsyncImagePainter(book!!.imageLinks?.thumbnail)

    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = paint,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(3f / 4f)
                    .clip(RoundedCornerShape(16.dp))
            )

            Spacer(modifier = Modifier.width(16.dp))
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = book!!.title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text(text = book!!.authors.joinToString(", ") ?: "Unknown Author", fontSize = 16.sp)
                Text(text = book!!.publishedDate, fontSize = 16.sp)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(top = 16.dp)
        ) {
            Text(text = "Описание", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = book!!.description ?: "Нет описания", fontSize = 16.sp)
        }
    }
}