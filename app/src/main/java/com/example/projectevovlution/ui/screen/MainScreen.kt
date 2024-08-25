package com.example.projectevovlution.ui.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projectevovlution.ui.route.Screen
import com.example.projectevovlution.ui.route.getStringId

@Composable
fun MainScreen(navController: NavController) {
    val list = listOf("Calculator Screen", "Notes Screen", "C")
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 22.dp)
            .padding(start = 12.dp, end = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(items = list) { data ->
            Column {
                Box(
                    contentAlignment = Alignment.Center, modifier = Modifier
                        .padding(vertical = 8.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                        .fillMaxSize()
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(vertical = 8.dp)
                    ) {
                        CreateText(data = data, navController)
                    }
                }
                Divider(
                    modifier = Modifier
                        .height(2.dp)
                        .padding(horizontal = 8.dp)
                        .background(Color.White)
                )
            }
        }
    }
}

@Composable
fun CreateText(data: String, navController: NavController) {
    Text(
        textAlign = TextAlign.Center,
        style = TextStyle(
            color = Color.Black,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ),
        modifier = Modifier.clickable {
            navController.navigate(
                data.getStringId().also {
                    Log.d("zzzz",it.toString())
                }.withData()
            )
        },
        text = data
    )
}

