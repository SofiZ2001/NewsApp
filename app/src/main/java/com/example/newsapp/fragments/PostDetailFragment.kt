package com.example.newsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.newsapp.fragments.PostListFragment.Companion.POST_DATA_CONTENT
import com.example.newsapp.fragments.PostListFragment.Companion.POST_DATA_TITLE
import com.example.newsapp.ui.screen.PostDetailScreen

class PostDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val title = arguments?.getString(POST_DATA_TITLE) ?: EMPTY
        val content = arguments?.getString(POST_DATA_CONTENT) ?: EMPTY
        return ComposeView(requireContext()).apply {
            setContent {
                PostDetailScreen(title, content)
            }
        }
    }

    companion object {
        private const val EMPTY = ""
    }
}