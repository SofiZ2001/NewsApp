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
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.domain.model.PostModel
import com.example.newsapp.R
import com.example.newsapp.ui.screen.PostListScreen
import com.example.newsapp.viewmodel.PostViewModel
import com.example.newsapp.viewmodel.PostViewModel.PostState.Success
import com.example.newsapp.viewmodel.PostData
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
                if (postState is Success) {
                    val posts = (postState as Success).posts
                    PostListScreen(
                        posts,
                        ::goToPostDetail,
                        ::goToUserList,
                        onSearchPost = { inputText -> viewModel.filterPost(inputText) })
                }
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
                putParcelable(
                    "POST_DATA",
                    PostData(postSelected.title, postSelected.content)
                )
            }
        )
    }

    private fun goToUserList() {
        findNavController().navigate(R.id.action_postList_to_userList)
    }
}