package com.example.newsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.ui.screen.MenuScreen

class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MenuScreen(::goToPostList, ::goToUserList)
            }
        }
    }

    private fun goToPostList() = findNavController().navigate(R.id.action_menu_to_postList)

    private fun goToUserList() = findNavController().navigate(R.id.action_menu_to_userList)
}