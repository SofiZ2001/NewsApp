package com.example.newsapp.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.model.PostModel
import com.example.newsapp.ui.common.TopBarApp

@Composable
fun PostListScreen(
    posts: List<PostModel> = emptyList(),
    onPostClick: (post: PostModel) -> Unit,
    onUserClick: () -> Unit,
    onSearchPost: (inputText: String) -> Unit
    ) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBarApp(showBackBtn = false, onUserClick = { onUserClick() }) },
        content = { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                var searchText by remember { mutableStateOf("") }
                TextField(
                    value = searchText,
                    onValueChange = {
                        searchText = it
                        onSearchPost(it)
                    },
                    label = { Text("Buscar noticia") },
                    modifier = Modifier.fillMaxWidth()
                )

                LazyColumn(
                ) {
                    items(posts) { post ->
                        NewsCard(post) { onPostClick(post) }
                    }
                }
            }
        }
    )
}

@Composable
fun NewsCard(post: PostModel, onPostClick: (post: PostModel) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .clickable { onPostClick(post) }
    ) {
        Column(modifier = Modifier.padding(4.dp)) {
            Text(
                post.title,
                textAlign = TextAlign.Start,
                fontSize = 32.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.padding(2.dp))
            HorizontalDivider(thickness = 1.dp)
            Spacer(modifier = Modifier.padding(2.dp))
            Text(
                post.content,
                textAlign = TextAlign.Start,
                fontSize = 24.sp,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}