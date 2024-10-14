package com.example.news.ui.screens.homescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.news.datalayer.model.Article
import com.example.news.ui.theme.LayoutBackground
import com.example.news.ui.theme.ListBackground

@Composable
fun CreateArticleList(newsList: List<Article>) {
    LazyColumn(
        modifier = Modifier
            .background(LayoutBackground)
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        items(items = newsList) {
            CreateArticle(it)
        }
    }
}

@Composable
fun CreateArticle(article: Article) {
    Column(
        modifier = Modifier
            .background(ListBackground)
            .padding(8.dp)
            .fillMaxWidth()
    ) {

        Text(
            modifier = Modifier.padding(top = 16.dp),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            text = article.title,
        )
    }
    Spacer(modifier = Modifier.height(10.dp))
}