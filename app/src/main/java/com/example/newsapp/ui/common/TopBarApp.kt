package com.example.newsapp.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.newsapp.ui.theme.DarkBlue

@Composable
fun TopBarApp() {
    Text(
        modifier = Modifier
            .background(DarkBlue)
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        text = "NewsApp",
        textAlign = TextAlign.Center,
        color = Color.White,
        fontWeight = FontWeight.ExtraBold
    )
}