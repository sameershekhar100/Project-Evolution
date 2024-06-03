package com.example.projectevovlution.ui.screen.calculatorscreen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectevovlution.ui.route.Screen

@Composable
fun InputKeyView() {
    val input = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "=", "AC")
    Column(modifier = Modifier.fillMaxWidth()) {
        var idx = 0
        repeat(3) {
            Row {
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
    Box(
        modifier = Modifier
            .padding(40.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        Text(
            modifier = Modifier
                .drawBehind {
                    drawCircle(
                        color = Color.LightGray,
                        radius = 98.0f
                    )
                },
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
}
