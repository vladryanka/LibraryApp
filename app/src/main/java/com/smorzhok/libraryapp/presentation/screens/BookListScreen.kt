package com.smorzhok.libraryapp.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smorzhok.libraryapp.R
import com.smorzhok.libraryapp.domain.Book

@Composable
fun BookListScreen() {
    // Пример данных (сами замените на ваш источник данных)
    val books = listOf(
        Book("https://via.placeholder.com/150", "Автор 1", "Книга 1"),
        Book("https://via.placeholder.com/150", "Автор 2", "Книга 2"),
        Book("https://via.placeholder.com/150", "Автор 3", "Книга 3"),
        Book("https://via.placeholder.com/150", "Автор 4", "Книга 4")
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // 2 колонки
        contentPadding = PaddingValues(16.dp) // Отступы для содержимого
    ) {
        items(books) { book ->
            BookCard(book = book)
        }
    }
}

@Composable
fun BookCard(book: Book) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Image(
            painter = painterResource(R.drawable.book_placeholder),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(3f / 4f)
                .clip(RoundedCornerShape(16.dp))
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = book.author,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = colorResource(R.color.gray),
            modifier = Modifier.padding(start = 4.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = book.title,
            fontSize = 12.sp,
            color = Color.Black,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BookCardPreview() {
    BookListScreen()
}