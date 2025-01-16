package com.example.newsapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.R
import com.example.newsapp.ui.theme.DarkBlue

@Composable
fun MenuScreen(onPostBtnClick: () -> Unit, onUserBtnClick: () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(DarkBlue, Color.Blue)
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            stringResource(R.string.menu_title),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 36.sp
        )
        Spacer(Modifier.height(12.dp))
        Button(
            onClick = { onPostBtnClick() },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White)
        ) { Text(stringResource(R.string.menu_post_button_text), color = DarkBlue) }
        Button(
            onClick = { onUserBtnClick() },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White)
        ) { Text(stringResource(R.string.menu_user_button_text), color = DarkBlue) }
    }
}