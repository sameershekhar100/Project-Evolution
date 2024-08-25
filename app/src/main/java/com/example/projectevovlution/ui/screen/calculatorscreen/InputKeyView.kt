package com.example.projectevovlution.ui.screen.calculatorscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectevovlution.R

@Composable
fun InputKeyView() {
    val input = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "=", "AC")
    Column(modifier = Modifier.fillMaxWidth()) {
        var idx = 0
        repeat(3) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
                for (i in 0..3) {
                    CreateInputText(input[idx])
                    idx++
                }
            }
        }
    }
}


@Composable
fun CreateInputText(input: String) {
    Text(
        modifier = Modifier.clip(CircleShape).background(Color.Blue).padding(20.dp),
        textAlign = TextAlign.Center,
        softWrap = false,
        style = TextStyle(
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ), text = input
    )
}

