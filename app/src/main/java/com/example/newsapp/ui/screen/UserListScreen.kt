package com.example.newsapp.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.model.UserModel
import com.example.newsapp.R
import com.example.newsapp.ui.common.TopBarApp
import com.example.newsapp.ui.theme.DarkBlue
import com.example.newsapp.viewmodel.UserViewModel.UserState
import com.example.newsapp.viewmodel.UserViewModel.UserState.Error
import com.example.newsapp.viewmodel.UserViewModel.UserState.Idle
import com.example.newsapp.viewmodel.UserViewModel.UserState.Success

@Composable
fun UserListScreen(
    state: UserState,
    onMapClick: (lat: Double, lng: Double) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBarApp() },
        content = { paddingValues ->
            Box(Modifier.padding(paddingValues)) {
                when (state) {
                    is Success -> {
                        val users = state.users
                        LazyColumn(
                            Modifier
                                .fillMaxWidth()
                        ) {
                            items(users) { user ->
                                with(user.address.geo) {
                                    UserCard(user) {
                                        onMapClick(
                                            lat.toDouble(),
                                            lng.toDouble()
                                        )
                                    }
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
fun UserCard(user: UserModel, onMapClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Icon(
                    modifier = Modifier.size(48.dp),
                    painter = painterResource(id = R.drawable.ic_user),
                    tint = DarkBlue,
                    contentDescription = "",
                )
                Column(modifier = Modifier.padding(horizontal = 4.dp)) {
                    Text(
                        "${user.firstName} ${user.lastName}",
                        textAlign = TextAlign.Start,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.padding(2.dp))
                    Text(
                        user.birthDate,
                        textAlign = TextAlign.Start,
                        fontSize = 12.sp
                    )
                }
            }

            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        onMapClick()
                    },
                painter = painterResource(id = R.drawable.ic_map),
                tint = DarkBlue,
                contentDescription = ""
            )
        }
    }
}