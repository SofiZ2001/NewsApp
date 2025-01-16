package com.example.newsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.domain.model.PostModel
import com.example.newsapp.R
import com.example.newsapp.ui.screen.PostListScreen
import com.example.newsapp.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostListFragment : Fragment() {
    private val viewModel: PostViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val postState by viewModel.postState.collectAsState()
                PostListScreen(
                    postState,
                    onPostClick = ::goToPostDetail,
                    onSearchPost = { inputText -> viewModel.filterPost(inputText) })
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPosts()
    }

    private fun goToPostDetail(postSelected: PostModel) {
        findNavController().navigate(
            R.id.action_postList_to_postDetail,
            Bundle().apply {
                putString(POST_DATA_TITLE, postSelected.title)
                putString(POST_DATA_CONTENT, postSelected.content)
            }
        )
    }

    companion object {
        const val POST_DATA_TITLE = "POST_DATA_TITLE"
        const val POST_DATA_CONTENT = "POST_DATA_CONTENT"
    }
}