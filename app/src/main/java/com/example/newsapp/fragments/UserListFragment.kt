package com.example.newsapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.newsapp.activity.MapActivity
import com.example.newsapp.ui.screen.UserListScreen
import com.example.newsapp.viewmodel.UserViewModel
import com.example.newsapp.viewmodel.UserViewModel.UserState.Success
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment : Fragment() {

    private val viewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val userState by viewModel.userState.collectAsState()
                if (userState is Success) {
                    val users = (userState as Success).users
                    UserListScreen(users, ::goToMap)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUsers()
    }

    private fun goToMap(lat: Double, lng: Double) {
        val intent = Intent(requireContext(), MapActivity::class.java).apply {
            putExtra("latitude", lat)
            putExtra("longitude", lng)
        }
       startActivity(intent)
    }

}