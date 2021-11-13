package com.mpost.app

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mpost.app.pojo.GenericResponse
import com.mpost.app.pojo.Subscriber
import com.mpost.app.retrofit.APIClient
import com.mpost.app.retrofit.APIInterface
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback;
@Composable
fun detailPhoneNumber(navController: NavController) {
    var phoneNumberValue =remember { mutableStateOf("") }
    val maxChar = 9
    val context = LocalContext.current
    var isError = remember { mutableStateOf(false) }

    Column (
        modifier = Modifier.padding(
            top= 65.dp
        ),
    ) {
        Text(
            text = "Enter your phone number",
            modifier = Modifier.padding(
                start = 24.dp
            ),
        )
        Row (
            modifier = Modifier.padding(
                start= 24.dp,
                end = 24.dp,
                top = 16.dp
            ),
        ){
            OutlinedTextField(
                value = "+254",
                onValueChange = { },
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        end = 5.dp
                    ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White,
                    focusedBorderColor = Color.DarkGray,
                    unfocusedBorderColor = Color.DarkGray
                ),
                shape = RoundedCornerShape(8.dp),
                singleLine = true,
                leadingIcon= {
                    Image(
                        painter = painterResource(id = R.mipmap.kenya_flag),
                        contentDescription = "App Logo",
                        modifier = Modifier.clickable {  }
                    )
                },
                enabled = false
            )
            OutlinedTextField(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                value = phoneNumberValue.value,
                onValueChange = {
                    if(it.length<=maxChar) {
                        phoneNumberValue.value = it
                    }
                    isError.value = it.length < maxChar
                },
                modifier = Modifier
                    .weight(2f)
                    .padding(
                        end = 5.dp
                    ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White,
                    focusedBorderColor = Color.DarkGray,
                    unfocusedBorderColor = Color.DarkGray,
                    textColor= Color.DarkGray
                ),
                shape = RoundedCornerShape(8.dp),
                singleLine = true
            )
        }
        if (isError.value) {
            Column  {
                Text(
                    text = "Minimum of 9 characters required",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 24.dp)
                )
            }
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
            Text(
                text = "by clicking 'GET PIN' you have read and agreed with our",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 40.dp,
                    ),
                fontSize = 14.sp
            )
            Text(
                text = "Terms and Conditions",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 130.dp,
                        bottom = 10.dp
                    ),
                fontSize = 14.sp
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                    ),
                onClick = {
                    generateOTP("254"+phoneNumberValue.value,navController,context)
                          },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = buttonPrimaryColor,
                    contentColor = Color.White
                )
            ) {
                Text(text = "GET PIN")
            }
        }
    }
}

fun generateOTP(mobileNumber: String,
                   navController: NavController,
                    context: Context,
) {

        val apiInterface = APIClient.client!!.create(APIInterface::class.java)
        val subscriberRequest = apiInterface.generateOTP(mobileNumber)
        val response = subscriberRequest?.enqueue(object : Callback<GenericResponse?> {
            override fun onResponse(
                call: Call<GenericResponse?>?,
                response: Response<GenericResponse?>
            ) {
                if (response.code() == 200) {
                    navController.navigate("enterOTP/$mobileNumber")
                }
            }

            override fun onFailure(call: Call<GenericResponse?>?, t: Throwable?) {
                Toast.makeText(
                    context,
                    "Network or phone number error",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

}



