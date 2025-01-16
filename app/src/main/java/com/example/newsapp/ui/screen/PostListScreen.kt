package com.example.newsapp.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.model.PostModel
import com.example.newsapp.R
import com.example.newsapp.ui.common.TopBarApp
import com.example.newsapp.viewmodel.PostViewModel
import com.example.newsapp.viewmodel.PostViewModel.PostState
import com.example.newsapp.viewmodel.PostViewModel.PostState.Error
import com.example.newsapp.viewmodel.PostViewModel.PostState.Idle
import com.example.newsapp.viewmodel.PostViewModel.PostState.Success

@Composable
fun PostListScreen(
    state: PostState,
    onPostClick: (post: PostModel) -> Unit,
    onSearchPost: (inputText: String) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBarApp() },
        content = { paddingValues ->
            Box(Modifier.padding(paddingValues)) {
                when (state) {
                    is Success -> {
                        val posts = state.posts
                        Column {
                            var searchText by remember { mutableStateOf("") }
                            TextField(
                                value = searchText,
                                onValueChange = {
                                    searchText = it
                                    onSearchPost(it)
                                },
                                label = { Text(stringResource(R.string.post_list_search_post_text)) },
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(Modifier.height(4.dp))
                            LazyColumn {
                                items(posts) { post ->
                                    NewsCard(post) { onPostClick(post) }
                                }
                            }
                        }
                    }

                    is Idle -> Text(
                        text = stringResource(R.string.service_loading_data),
                        textAlign = TextAlign.Center
                    )

                    is Error -> Text(
                        text = stringResource(R.string.service_error_message),
                        textAlign = TextAlign.Center
                    )
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
            .padding(vertical = 4.dp, horizontal = 12.dp)
            .clickable { onPostClick(post) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
        ) {
            Text(
                post.title,
                textAlign = TextAlign.Start,
                fontSize = 32.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(2.dp))
            HorizontalDivider(thickness = 1.dp)
            Spacer(modifier = Modifier.height(2.dp))
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