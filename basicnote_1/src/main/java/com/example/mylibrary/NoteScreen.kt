package com.example.mylibrary

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.mylibrary.GlobalState.sharedPreferences


object GlobalState {
    lateinit var sharedPreferences: SharedPreferences
}

@Composable
fun NoteScreen() {
    val context = LocalContext.current
    sharedPreferences = remember {
        context.getSharedPreferences("MyPrefs", MODE_PRIVATE)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NotesTopBar()
        FourColumnLayout()
    }
}

internal fun getSavedValue(id: String = "breakfast") = sharedPreferences.getString(id, "") ?: ""
internal fun saveValue(value: String, id: String = "breakfast") {
    with(sharedPreferences.edit()) {
        putString(id, value)
        apply()
    }
}

@Composable
fun FourColumnLayout() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceAround // Evenly distributes the columns
    ) {
        CreateCell("Breakfast")
        CreateCell("Lunch")
        CreateCell("Snack")
        CreateCell("Dinner")
    }
}

@Composable
fun CreateCell(mealType: String) {
    Column(modifier = Modifier.padding(vertical = 20.dp)) {
        Text(text = mealType)
        CustomOutlinedTextField(mealType)
        Spacer(modifier = Modifier.padding(8.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesTopBar() {
    TopAppBar(
        title = { Text("Notes") },
        modifier = Modifier.shadow(8.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomOutlinedTextField(mealType: String) {
    var breakfast by remember { mutableStateOf("") }
    LaunchedEffect(Unit) {
        breakfast = getSavedValue(mealType)
    }
    OutlinedTextField(
        value = breakfast,
        onValueChange = { x ->
            breakfast = x
            saveValue(breakfast, mealType)
        },
        label = { Text(text = "Enter Meal Name") },
        shape = RoundedCornerShape(8.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            focusedBorderColor = Color.Black,
            unfocusedTextColor = Color.Black,
            unfocusedBorderColor = Color.Black,
            focusedLabelColor = Color.Black,
            unfocusedLabelColor = Color.Black,
            cursorColor = Color.Gray
        )
    )
}