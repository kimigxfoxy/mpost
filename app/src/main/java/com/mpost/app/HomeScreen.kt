package com.mpost.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun homeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 55.dp
            ),
    ) {
        Image(
            painter = painterResource(id = R.drawable.splash_portrait),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
    }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(
                bottom = 16.dp
            ),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                ),
            onClick = { navController.navigate("enterPhoneNumber") },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = buttonPrimaryColor,
                contentColor = Color.White
            )
        ) {
            Text(text = "START USING MPOST")
        }
    }
}