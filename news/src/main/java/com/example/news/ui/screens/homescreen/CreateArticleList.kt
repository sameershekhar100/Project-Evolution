package com.example.news.ui.screens.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.news.R
import com.example.news.datalayer.model.Article
import com.example.news.ui.theme.LayoutBackground
import com.example.news.ui.theme.ListBackground
import com.example.news.ui.theme.ListText

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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.weight(2f),
                painter = rememberAsyncImagePainter(
                    model = article.urlToImage,
                    placeholder = painterResource(R.drawable.outline_save_24),
                    error = painterResource(id = R.drawable.baseline_star_outline_24),
                    contentScale = ContentScale.FillBounds
                ),
                contentDescription = "Image Not Available"
            )
            Column(
                modifier = Modifier
                    .weight(3f)
                    .padding(start = 8.dp)
            )
            {
                article.description?.let {
                    Text(
                        text = it,
                        color = ListText,
                        fontSize = 15.sp,
                        maxLines = 5
                    )
                }
                article.publishedAt?.let { Text(text = it, color = ListText, fontSize = 12.sp) }
                Text(text = article.source.name, color = ListText, fontSize = 12.sp)
            }
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}