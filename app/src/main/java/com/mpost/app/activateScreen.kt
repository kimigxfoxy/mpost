package com.mpost.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun activateRegistration(navController: NavController, mobileNumber:String) {
    var buttonPrimaryColor= Color(android.graphics.Color.parseColor("#1774A7"))
    var buttonPrimaryColor2= Color(android.graphics.Color.parseColor("#D9EEFA"))

    Column {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 55.dp,
                )
                .drawBehind {
                    val strokeWidth = 3f
                    val y = size.height - strokeWidth / 2
                    drawLine(
                        Color.Gray,
                        Offset(0f, y),
                        Offset(size.width, y),
                        strokeWidth
                    )
                }){

                Column{
                Text(
                    text = "Activate your virtual postal box",
                    modifier = Modifier.padding(
                        start= 10.dp,
                        top = 10.dp,
                        bottom=5.dp
                    ),
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp
                )
                Text(
                    text = "Activate your postal box by paying the amount below",
                    modifier = Modifier.padding(
                        start= 10.dp,
                        top = 5.dp,
                        bottom=10.dp
                    ),
                   color = Color.LightGray

                )
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            content = {
                Card(elevation = 4.dp, modifier = Modifier.padding(all = 10.dp)) {
                    Row(modifier = Modifier
                        .padding(all = 10.dp)
                        .fillMaxWidth()) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Total amount", color = Color.Gray)
                            Text("Ksh 300", fontWeight = FontWeight.W700)
                        }
                        Column(
                            horizontalAlignment = Alignment.End,
                            modifier = Modifier.weight(1f)
                        ) {
                            Box(
                                modifier = Modifier
                                    .background(
                                        color = buttonPrimaryColor2,
                                        shape = RoundedCornerShape(4.dp)
                                    )
                                    .padding(all = 2.dp),) {
                                Text("Yearly payment", color = buttonPrimaryColor)
                            }
                        }
                    }
                }

                Card(elevation = 4.dp, modifier = Modifier
                    .padding(all = 10.dp)
                    .fillMaxWidth()) {
                    Column() {
                       Text("Pay now through M-PESA", color = Color.Gray,
                           modifier = Modifier.padding(start=25.dp,top=10.dp,bottom = 10.dp)
                       )
                       Divider(
                           color = Color.LightGray,
                           thickness = 2.dp,
                        )
                       Text("You will receive M-PESA push to the following \nnumber: "+mobileNumber,
                           modifier = Modifier.padding(start=25.dp,top=10.dp,bottom = 10.dp,end = 10.dp)
                       )
                       Divider(color = Color.LightGray, thickness = 2.dp)
                       Text("Change number",modifier = Modifier.padding(start=25.dp,top=10.dp,bottom = 10.dp))
                       OutlinedTextField(
                           keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                           modifier = Modifier.fillMaxWidth().padding(start=25.dp,bottom = 10.dp,end = 25.dp),
                           value = "",
                           onValueChange = {

                           },
                           colors = TextFieldDefaults.outlinedTextFieldColors(
                               backgroundColor = Color.White,
                               focusedBorderColor = Color.LightGray,
                               unfocusedBorderColor = Color.LightGray,
                               textColor= Color.DarkGray
                           ),
                           shape = RoundedCornerShape(8.dp),
                           singleLine = true
                       )
                   }
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

                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = com.mpost.app.buttonPrimaryColor,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "INITIATE PAYMENT")
                    }
                }
            }

        )
    }
}