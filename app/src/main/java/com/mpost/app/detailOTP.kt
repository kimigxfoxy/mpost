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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mpost.app.pojo.GenericResponse
import com.mpost.app.pojo.ValidateOtp
import com.mpost.app.retrofit.APIClient
import com.mpost.app.retrofit.APIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun detailOTP(navController: NavController,mobileNumber:String) {
    var sixDigitCode = remember { mutableStateOf("") }
    val maxChar = 6
    val context = LocalContext.current
    var isError = remember { mutableStateOf(true) }
    var isErrorFirstInput=remember { mutableStateOf(false) }

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
            Image(
                painter = painterResource(id = R.mipmap.chat_bubble_sms),
                contentDescription = null,
                modifier = Modifier.padding(
                    start= 24.dp,
                )
            )
            Column{
                Text(
                    text = "Code sent to $mobileNumber",
                    modifier = Modifier.padding(
                        start= 10.dp,
                        top = 10.dp,
                        bottom=5.dp
                    )
                )
                Text(
                    text = "Edit number",
                    modifier = Modifier.padding(
                        start= 10.dp,
                        top = 5.dp,
                        bottom=10.dp
                    ).clickable(
                        onClick = {
                            navController.navigate("enterPhoneNumber")                        }),
                    color = buttonPrimaryColor
                )
            }
        }
        Row (
            modifier = Modifier.padding(
                start= 24.dp,
                end = 24.dp,
                top = 16.dp
            ),
        ){
            OutlinedTextField(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                value = sixDigitCode.value,
                onValueChange = {
                    isErrorFirstInput.value =true
                    if(it.length<=maxChar) sixDigitCode.value = it
                    isError.value = it.length < maxChar
                },
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
        if (isError.value && isErrorFirstInput.value) {
            Column  {
                Text(
                    text = "Minimum of 6 characters required",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 24.dp)
                )
            }
        }
//        Column(
//            modifier = Modifier.padding(
//                start= 24.dp,
//            ),
//        ){
//            Text(
//                modifier = Modifier.padding(
//                    top= 5.dp,
//                ),
//                text = "Resend code in 25s",
//                color = Color.DarkGray
//            )
//        }
        if (!isError.value) {
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
                        validateOTP(
                            ValidateOtp(mobileNumber, sixDigitCode.value),
                            navController,
                            context
                        )
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = buttonPrimaryColor,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "SUBMIT")
                }
            }
        }
    }
}

fun validateOTP(validateOtp: ValidateOtp,
                navController: NavController,
                context: Context
) {
    val apiInterface = APIClient.client!!.create(APIInterface::class.java)
    val subscriberRequest = apiInterface.validateOTP(validateOtp)
    val response = subscriberRequest?.enqueue(object : Callback<GenericResponse?> {
        override fun onResponse(
            call: Call<GenericResponse?>?,
            response: Response<GenericResponse?>
        ) {
            if (response.code()== 200) {
                navController.navigate("enterPersonal/"+validateOtp.mobileNumber)
            }else{
                Toast.makeText(
                    context,
                    "OTP is expired or invalid",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        override fun onFailure(call: Call<GenericResponse?>?, t: Throwable?) {
            Toast.makeText(
                context,
                "Network error",
                Toast.LENGTH_SHORT
            ).show()
        }
    })
}