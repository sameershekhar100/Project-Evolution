package com.example.news.ui.screens.homescreen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.news.datalayer.util.Resource

@Composable
fun HomeScreen(viewModel: HomeScreenViewModel = hiltViewModel<HomeScreenViewModel>()) {
    val apiResponse = viewModel.newsList.collectAsState().value
    var isLoading by rememberSaveable { mutableStateOf(false) }
    val country = "us"
    val page = 1
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.getNewsHeadLines(country, page)
    }
    InitializeProgressBar(isLoading)
    when (apiResponse) {
        is Resource.Success -> {
            apiResponse.data?.let {
                if (it.articles.isEmpty()) CreateEmptyList()
                else CreateArticleList(newsList = it.articles
//                    .filterNot { article -> article.title.contains("[Removed]") }
                )
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
    CircularProgressIndicator(
        modifier = Modifier.width(64.dp),
        color = MaterialTheme.colorScheme.secondary,
        trackColor = MaterialTheme.colorScheme.surfaceVariant,
    )
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

