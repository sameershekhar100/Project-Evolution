package com.example.news.presentationlayer.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.news.datalayer.model.APIResponse
import com.example.news.datalayer.util.Resource
import com.example.news.domainlayer.usecase.GetNewsHeadlinesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val application: Application,
    private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase
) : AndroidViewModel(application) {
    val n = MutableLiveData<Resource<APIResponse>>()

    fun getNewsHeadLines(country: String, page: Int) =
        viewModelScope.launch(Dispatchers.IO) {
            n.postValue(Resource.Loading())
            runCatching {
                if (isOnline()) {
                    n.postValue(getNewsHeadlinesUseCase.execute(country, page))
                } else
                    n.postValue(Resource.Error("Internet Not Available"))
            }.onFailure {
                n.postValue(Resource.Error(it.message.toString()))
            }
        }

    fun isOnline(): Boolean {
        val context = application.applicationContext
        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connMgr.activeNetworkInfo
        return networkInfo?.isConnected == true
    }
}