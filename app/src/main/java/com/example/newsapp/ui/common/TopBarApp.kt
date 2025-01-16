package com.example.newsapp.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.newsapp.R
import com.example.newsapp.ui.theme.DarkBlue

@Composable
fun TopBarApp(
    showBackBtn: Boolean = true,
    showUserBtn: Boolean = true,
    onUserClick: (() -> Unit)? = null
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(DarkBlue)
            .padding(vertical = 8.dp, horizontal = 4.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    )
    {
        if (showBackBtn)
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.back),
                tint = Color.White,
                contentDescription = "",
            )

        Text(
            modifier = Modifier,
            text = "NewsApp",
            textAlign = TextAlign.Center,
            color = Color.White,
            fontWeight = FontWeight.ExtraBold
        )

        if (showUserBtn)
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        onUserClick?.let {
                            it()
                        }
                    },
                painter = painterResource(id = R.drawable.ic_user),
                tint = Color.White,
                contentDescription = "",
            )
    }
}