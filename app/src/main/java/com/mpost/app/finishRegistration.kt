package com.mpost.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mpost.app.pojo.ValidateOtp


@Composable
fun finishRegistration(navController: NavController,mobileNumber:String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.mipmap.success_mail),
            contentDescription = null,
            modifier = Modifier.size(
                300.dp,
                300.dp
            )
        )
        Text(
            text = "Successfully registered",
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp
        )
        Text(
            text = "You have successfully registered to MPOST",
            color = Color.LightGray
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                bottom = 16.dp
            ),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                ),
            onClick = {
                navController.navigate("activateRegistration/" + mobileNumber)
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = buttonPrimaryColor,
                contentColor = Color.White
            )
        ) {
            Text(text = "CONTINUE")
        }
    }
}