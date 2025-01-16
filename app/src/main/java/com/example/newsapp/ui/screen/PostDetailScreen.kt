package com.example.newsapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.newsapp.ui.common.TopBarApp
import com.example.newsapp.viewmodel.PostData


@Composable
fun PostDetailScreen(post: PostData) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBarApp(showBackBtn = false) },
        content = { paddingValues ->
            ContentDetailScreen(paddingValues, post.title, post.content)
        }
    )
}

@Composable
fun ContentDetailScreen(paddingValues: PaddingValues, title: String, content: String) {
    Column(modifier = Modifier.padding(paddingValues)) {
        Text(
            title, textAlign = TextAlign.Start,
            fontFamily = FontFamily.Serif,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            content,
            textAlign = TextAlign.Start,
            fontSize = 12.sp
        )

    }

}