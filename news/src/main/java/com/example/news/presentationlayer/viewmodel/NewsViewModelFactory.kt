package com.example.news.presentationlayer.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.news.domainlayer.usecase.GetNewsHeadlinesUseCase

//class NewsViewModelFactory(
//    private val application: Application,
//    private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase
//):ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return NewsViewModel(application,getNewsHeadlinesUseCase) as T
//    }
//}