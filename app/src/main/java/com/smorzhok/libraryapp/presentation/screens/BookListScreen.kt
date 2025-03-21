package com.smorzhok.libraryapp.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.smorzhok.libraryapp.R
import com.smorzhok.libraryapp.data.entities.BookDbModel

@Composable
fun BookListScreen(
    books: List<BookDbModel>,
    navController: NavController,
    mainViewModel: MainViewModel
) {
    if (books.isNotEmpty()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
        ) {
            items(books) { book ->
                BookCard(book = book, navController, mainViewModel)
            }
        }
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Нет результатов",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
        }
    }
}

@Composable
fun BookCard(book: BookDbModel, navController: NavController, mainViewModel: MainViewModel) {
    val paint = if (book.imageLinks?.thumbnail == null)
        painterResource(R.drawable.book_placeholder) else
        rememberAsyncImagePainter(book.imageLinks.thumbnail)
    val ldId = MutableLiveData<String>()

    val isLiked = remember { mutableStateOf(false) }

    LaunchedEffect(book.title) {
        mainViewModel.selectBook(book.title)
        ldId.value = mainViewModel.selectedBook.value
        val bookLiked = mainViewModel.isBookLiked(book.title)
        isLiked.value = bookLiked
    }

    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate("book_detail/${ldId.value}")
            }
    ) {
        Column(horizontalAlignment = Alignment.Start) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopEnd
            ) {
                Image(
                    painter = paint,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(3f / 4f)
                        .clip(RoundedCornerShape(32.dp))
                )

                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .requiredSize(32.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .clickable {
                            if (isLiked.value) {
                                mainViewModel.dislikeBook(book)
                            } else {
                                mainViewModel.likeBook(book)
                            }
                            isLiked.value = !isLiked.value
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(MyScreen.Favorites.icon),
                        contentDescription = "Like",
                        tint = Color.Red,
                        modifier = Modifier.requiredSize(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = book.authors.toString(),
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
}