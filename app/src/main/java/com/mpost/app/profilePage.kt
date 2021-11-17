package com.mpost.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun profilePage(
    navController: NavController,
    names:String,
    mobileNumber:String,
    city: String,
    area:String,
    postalCode: String,
    expiryDate:String,
    isPaid: String,
    isExpired:String,
) {

    var buttonPrimaryColor= Color(android.graphics.Color.parseColor("#1774A7"))
    var buttonPrimaryColor2= Color(android.graphics.Color.parseColor("#D9EEFA"))
    val sdf = SimpleDateFormat("dd-mm-yyyy")
    val currentDate = sdf.format(Date())

    var active="Not Active"
    if(isExpired=="false" && isPaid=="true"){
        active="Active"
    }


    Column {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 55.dp,
                )){

            Column{
                Text(
                    text = "Welcome ${names}",
                    modifier = Modifier.padding(
                        start= 20.dp,
                        top = 10.dp,
                        bottom=5.dp
                    ),
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp
                )
                Text(
                    text = currentDate,
                    modifier = Modifier.padding(
                        start= 20.dp,
                        top = 5.dp,
                        bottom=10.dp
                    ),
                    color = androidx.compose.ui.graphics.Color.LightGray

                )
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            content = {

                Card(elevation = 4.dp, modifier = Modifier
                    .padding(top = 10.dp, start = 20.dp,end = 20.dp)
                    .fillMaxWidth()) {
                    Column() {
                        Text("Your details", color = androidx.compose.ui.graphics.Color.Gray,
                            modifier = Modifier.padding(start=25.dp,top=10.dp,bottom = 10.dp)
                        )
                        Divider(
                            color = androidx.compose.ui.graphics.Color.LightGray,
                            thickness = 2.dp,
                        )
                        Row(modifier = Modifier
                            .padding(all = 10.dp)
                            .fillMaxWidth()) {
                            Column(
                                modifier = Modifier.weight(1f).padding(start = 20.dp)
                            ) {
                                Text("City", color = Color.Gray)
                                Text(city, fontWeight = FontWeight.W700)
                            }
                            Column(
                                modifier = Modifier.weight(1f).padding(start = 20.dp)
                            ) {
                                Text("Area", color = Color.Gray)
                                Text(area, fontWeight = FontWeight.W700)
                            }
                        }
                        Row(modifier = Modifier
                            .padding(all = 10.dp)
                            .fillMaxWidth()) {
                            Column(
                                modifier = Modifier.weight(1f).padding(start = 20.dp)
                            ) {
                                Text("Postal code", color = Color.Gray)
                                Text(city, fontWeight = FontWeight.W700)
                            }
                            Column(
                                modifier = Modifier.weight(1f).padding(start = 20.dp)
                            ) {
                                Text("Box status", color = Color.Gray)
                                Text(active, fontWeight = FontWeight.W700)
                            }
                        }

                        Row(modifier = Modifier
                            .padding(all = 10.dp)
                            .fillMaxWidth()) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.weight(1f)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .background(
                                            color = buttonPrimaryColor2,
                                            shape = RoundedCornerShape(4.dp)
                                        )
                                        .padding(all = 2.dp),) {
                                    Text("Your box is active until $expiryDate", color = buttonPrimaryColor)
                                }
                            }
                        }

                    }
                }
            }

        )
    }
}