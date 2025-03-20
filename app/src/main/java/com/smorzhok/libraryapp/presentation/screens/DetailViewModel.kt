package com.smorzhok.libraryapp.presentation.screens

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smorzhok.libraryapp.data.entities.BookDbModel
import com.smorzhok.libraryapp.data.remote.RepositoryProvider
import kotlinx.coroutines.launch

class DetailViewModel: ViewModel() {
    private val repository = RepositoryProvider.getRepository()

    private val _book = MutableLiveData<BookDbModel>()
    val book: LiveData<BookDbModel> get() = _book
    fun getBookById(id:String) {
        viewModelScope.launch {
            _book.postValue( repository.getBookById(id) )
            repository.getBookById(id)
            Log.d("Book", _book.value.toString())
        }
    }
}