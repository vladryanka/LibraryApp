package com.smorzhok.libraryapp.presentation.screens

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.smorzhok.libraryapp.data.database.AppDatabase
import com.smorzhok.libraryapp.data.entities.BookDbModel
import com.smorzhok.libraryapp.data.remote.RepositoryProvider
import com.smorzhok.libraryapp.domain.ContainsBookByIDUseCase
import com.smorzhok.libraryapp.domain.DislikeBookUseCase
import com.smorzhok.libraryapp.domain.GetBooksByTitleUseCase
import com.smorzhok.libraryapp.domain.LikeBookUseCase
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RepositoryProvider.getRepository()
    private val getBooksByTitle: GetBooksByTitleUseCase = GetBooksByTitleUseCase(repository)
    private val likeBookUseCase: LikeBookUseCase = LikeBookUseCase(repository)
    private val containsBookByIDUseCase: ContainsBookByIDUseCase = ContainsBookByIDUseCase(repository)
    private val dislikeBookUseCase: DislikeBookUseCase = DislikeBookUseCase(repository)
    private val bookDbModelDao = AppDatabase.getInstance(application).bookDbModelDao()

    private val _bookList = MutableLiveData<List<BookDbModel>>()
    val bookList: LiveData<List<BookDbModel>> get() = _bookList

    private val _favoriteBooks = MutableLiveData<List<BookDbModel>>()
    val favoriteBooks: LiveData<List<BookDbModel>> get() = _favoriteBooks

    private var _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _resAdd = MutableLiveData(false)
    val resAdd: LiveData<Boolean> get() = _resAdd

    private val _selectedBook = MutableLiveData<String?>()
    val selectedBook: LiveData<String?> get() = _selectedBook

    private val _resDelete = MutableLiveData(false)
    val resDelete: LiveData<Boolean> get() = _resDelete

    init {
        loadFavoriteBooks()
    }

    fun likeBook(book: BookDbModel) {
        viewModelScope.launch {
            val result = likeBookUseCase(book)
            _resAdd.postValue(result)
            loadFavoriteBooks()
        }
    }

    fun dislikeBook(book: BookDbModel) {
        viewModelScope.launch {
            val result = dislikeBookUseCase(book)
            _resDelete.postValue(result)
            loadFavoriteBooks()
        }
    }

    suspend fun isBookLiked(title:String): Boolean {
        return containsBookByIDUseCase(title)
    }
    fun selectBook(title: String) {
        viewModelScope.launch {
            val result = repository.getVolumeIdByTitle(title)
            _selectedBook.postValue(result)
        }
    }

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

    fun loadFavoriteBooks() {
        viewModelScope.launch {
            _favoriteBooks.postValue(bookDbModelDao.getFavoriteBooks())
        }
    }
}