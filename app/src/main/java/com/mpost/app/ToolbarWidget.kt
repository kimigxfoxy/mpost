package com.mpost.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

@Composable
fun ToolbarWidget() {
    TopAppBar(
        title = {
            Text(
                text = "",
            )
        },
        backgroundColor = Color.White,
        actions = {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.mipmap.mpost_logo),
                    contentDescription = "App Logo",
                    modifier = Modifier.clickable {  }
                )
            }
        }
    )
}