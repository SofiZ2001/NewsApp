package com.example.newsapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.newsapp.ui.common.TopBarApp


@Composable
fun PostDetailScreen(title: String, content: String) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBarApp() },
        content = { paddingValues ->
            ContentDetailScreen(paddingValues, title, content)
        }
    )
}

@Composable
fun ContentDetailScreen(paddingValues: PaddingValues, title: String, content: String) {
    val scrollState = rememberScrollState()
    Column(modifier = Modifier
        .padding(paddingValues)
        .verticalScroll(scrollState)) {
        Text(
            title, textAlign = TextAlign.Start,
            fontFamily = FontFamily.Serif,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
        HorizontalDivider()
        Text(
            content,
            textAlign = TextAlign.Start,
            fontSize = 24.sp
        )
    }
}