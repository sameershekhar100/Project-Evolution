package com.example.projectevovlution.ui.screen.calculatorscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@Preview
fun CalculatorScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 22.dp, horizontal = 12.dp),
    ) {
        ResultScreen()
        InputKeyView()
    }
}

@Composable
fun ResultScreen() {
    Box(
        modifier = Modifier
            .padding(top = 138.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomEnd
    ) {
        Text(modifier = Modifier.padding(end=22.dp),
            textAlign = TextAlign.End,
            style = TextStyle(
                color = Color.White,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal
            ), text = "0"
        )
    }
}
