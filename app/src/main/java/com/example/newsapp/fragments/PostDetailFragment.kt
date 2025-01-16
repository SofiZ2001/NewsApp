package com.example.newsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.newsapp.ui.screen.PostDetailScreen
import com.example.newsapp.viewmodel.PostData

class PostDetailFragment : Fragment() {
    private lateinit var post: PostData

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        post = arguments?.getParcelable("POST_DATA") ?: throw IllegalArgumentException("")
        return ComposeView(requireContext()).apply {
            setContent {
                PostDetailScreen(post)
            }
        }
    }
}