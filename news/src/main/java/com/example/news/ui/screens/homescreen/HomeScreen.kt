package com.example.news.ui.screens.homescreen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.news.datalayer.model.Article
import com.example.news.datalayer.util.Resource
import com.example.news.presentationlayer.viewmodel.HomeScreenViewModel
import kotlinx.coroutines.flow.map

@Composable
fun HomeScreen(viewModel: HomeScreenViewModel = hiltViewModel<HomeScreenViewModel>()) {
    var articleList by remember { mutableStateOf(listOf<Article>()) }
    val apiResponse = viewModel.newsList.collectAsState().value
    var isLoading by rememberSaveable { mutableStateOf(false) }
    val lazyListState = rememberLazyListState()
    val country = "us"
    var page = 1
    LaunchedEffect(Unit) {
        viewModel.getNewsHeadLines(country, page)
    }
    InitializeProgressBar(isLoading)
    when (apiResponse) {
        is Resource.Success -> {
            apiResponse.data?.let {
                Log.v("zzzzz", it.articles.size.toString())
                articleList = articleList + it.articles
                if (articleList.isEmpty()) CreateEmptyList()
                else {
                    val shouldLoadMore = remember {
                        derivedStateOf {
                            val totalItemsCount = lazyListState.layoutInfo.totalItemsCount
                            val lastVisibleItemIndex =
                                lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
                            lastVisibleItemIndex >= (totalItemsCount)
                        }
                    }
                    LaunchedEffect(lazyListState) {
                        snapshotFlow { lazyListState.layoutInfo.visibleItemsInfo }
                            .map { visibleItems ->
                                val lastVisibleItemIndex = visibleItems.lastOrNull()?.index
                                val totalItemsCount = lazyListState.layoutInfo.totalItemsCount

                                if (lastVisibleItemIndex != null && lastVisibleItemIndex == totalItemsCount - 1 && !isLoading) {
                                    isLoading = true
                                    // Fetch next page
                                    page++
                                    viewModel.getNewsHeadLines(country, page)
                                    articleList = articleList + it.articles
                                }
                            }.collect {}
                    }
                    CreateArticleList(articleList, lazyListState)

                }
            }
            isLoading = false
        }

        is Resource.Error -> {
            apiResponse.message?.let {
                CreateEmptyList()
                ShowToast(it)
            }
            isLoading = false
        }

        is Resource.Loading -> {
            isLoading = true
        }
    }
}

@Composable
fun InitializeProgressBar(isLoading: Boolean) {
    if (!isLoading) return
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(64.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }
}

@Composable
fun ShowToast(message: String) {
    val context = LocalContext.current
    Toast.makeText(
        context,
        "An error occurred $message",
        Toast.LENGTH_SHORT
    )
        .show()
}

@Composable
fun CreateEmptyList() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            text = "No News",
        )
    }
}

