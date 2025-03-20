package com.smorzhok.libraryapp

import android.app.Application
import com.smorzhok.libraryapp.data.remote.RepositoryProvider

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        RepositoryProvider.initialize(applicationContext)
    }
}