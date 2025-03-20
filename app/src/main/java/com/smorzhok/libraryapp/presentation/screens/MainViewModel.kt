package com.smorzhok.libraryapp.presentation.screens

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.smorzhok.libraryapp.data.database.AppDatabase
import com.smorzhok.libraryapp.data.entities.BookDbModel
import com.smorzhok.libraryapp.data.remote.RepositoryProvider
import com.smorzhok.libraryapp.domain.GetBooksByTitleUseCase
import com.smorzhok.libraryapp.domain.LikeBookUseCase
import com.smorzhok.libraryapp.domain.UnlikeBookUseCase
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RepositoryProvider.getRepository()
    private val getBooksByTitle: GetBooksByTitleUseCase = GetBooksByTitleUseCase(repository)
    private val likeBookUseCase: LikeBookUseCase = LikeBookUseCase(repository)
    private val unlikeBookUseCase: UnlikeBookUseCase = UnlikeBookUseCase(repository)
    private val bookDbModelDao = AppDatabase.getInstance(application).bookDbModelDao()

    private val _bookList = MutableLiveData<List<BookDbModel>>()
    val bookList: LiveData<List<BookDbModel>> get() = _bookList

    private var _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage

    private val _selectedBook = MutableLiveData<BookDbModel?>()
    val selectedBook: LiveData<BookDbModel?> get() = _selectedBook

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun loadBooksByTitle(title: String) {
        _isLoading.value = true
        viewModelScope.launch {
            _errorMessage.value = null
            val result = getBooksByTitle(title)
            if (result.second != null) {
                _bookList.value = result.second

            }
            _errorMessage.value = result.first
            _isLoading.value = false
        }
    }

    fun selectBook(id: Int) {
        viewModelScope.launch {
            val result = repository.getBookById(id)
            _selectedBook.value = result
        }
    }

}