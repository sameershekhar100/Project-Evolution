package com.example.news.presentationlayer.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.datalayer.model.APIResponse
import com.example.news.datalayer.util.Resource
import com.example.news.domainlayer.usecase.GetNewsHeadlinesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val application: Application,
    private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase
) : ViewModel() {
    private val _newsList = MutableStateFlow<Resource<APIResponse>>(Resource.Loading())
    val newsList = _newsList.asStateFlow()
    fun getNewsHeadLines(country: String, page: Int) =
        viewModelScope.launch(Dispatchers.IO) {
            _newsList.emit(Resource.Loading())
            runCatching {
                if (isOnline()) {
                    _newsList.emit(getNewsHeadlinesUseCase.execute(country, page))
                } else
                    _newsList.emit(Resource.Error("Internet Not Available"))
            }.onFailure {
                _newsList.emit(Resource.Error(it.message.toString()))
            }
        }.invokeOnCompletion { //Need to cancel the connection callback
        }

    private fun isOnline(): Boolean {
        val context = application.applicationContext
        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connMgr.activeNetworkInfo
        return networkInfo?.isConnected == true
    }
}