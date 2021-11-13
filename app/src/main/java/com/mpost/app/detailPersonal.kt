package com.mpost.app

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mpost.app.pojo.GenericResponse
import com.mpost.app.pojo.Subscriber
import com.mpost.app.retrofit.APIClient
import com.mpost.app.retrofit.APIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun detailPersonal(navController: NavController,mobileNumber:String) {
    var firstName = remember { mutableStateOf("") }
    var lastName =remember { mutableStateOf("") }
    var idNumber =remember { mutableStateOf("") }
    var postalCode =remember { mutableStateOf("") }
    var emailAddress =remember { mutableStateOf("") }
    val languageOptions: List<String> = listOf("Male", "Female")
    var selectedGender=""
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 55.dp,
            )
    ) {
        item {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 10.dp,
                        start = 24.dp,
                        bottom = 5.dp,
                        end = 24.dp,
                    )
            ){
                Column{
                    Text(
                        text = "Enter your personal details",
                        fontSize = 28.sp
                    )
                    Text(
                        text = "Please provide your personal information below",
                        color = Color.LightGray
                    )

                    Text(
                        text = "First name",
                        modifier = Modifier.padding(
                            top = 15.dp
                        )
                    )
                    OutlinedTextField(
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        modifier = Modifier.fillMaxWidth(),
                        value = firstName.value,
                        onValueChange = { firstName.value = it },
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
            }
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 10.dp,
                        start = 24.dp,
                        bottom = 5.dp,
                        end = 24.dp,
                    )
            ) {
                Column {
                    Text(
                        text = "Last name",
                    )
                    OutlinedTextField(
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        modifier = Modifier.fillMaxWidth(),
                        value = lastName.value,
                        onValueChange = { lastName.value = it },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Color.White,
                            focusedBorderColor = Color.DarkGray,
                            unfocusedBorderColor = Color.DarkGray,
                            textColor = Color.DarkGray
                        ),
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true
                    )
                }
            }
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 10.dp,
                        start = 24.dp,
                        bottom = 5.dp,
                        end = 24.dp,
                    )
            ) {
                Column {
                    Text(
                        text = "ID number",
                    )
                    OutlinedTextField(
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth(),
                        value = idNumber.value,
                        onValueChange = { idNumber.value = it },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Color.White,
                            focusedBorderColor = Color.DarkGray,
                            unfocusedBorderColor = Color.DarkGray,
                            textColor = Color.DarkGray
                        ),
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true
                    )
                }
            }
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 10.dp,
                        start = 24.dp,
                        bottom = 5.dp,
                        end = 24.dp,
                    )
            ) {
                Column {
                    Text(
                        text = "Preferred postal code",
                    )
                    OutlinedTextField(
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth(),
                        value = postalCode.value,
                        onValueChange = { postalCode.value = it },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Color.White,
                            focusedBorderColor = Color.DarkGray,
                            unfocusedBorderColor = Color.DarkGray,
                            textColor = Color.DarkGray
                        ),
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true
                    )
                }
            }
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 10.dp,
                        start = 24.dp,
                        bottom = 5.dp,
                        end = 24.dp,
                    )
            ) {
                Column {
                    Text(
                        text = "Email (Optional)",
                    )
                    OutlinedTextField(
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        modifier = Modifier.fillMaxWidth(),
                        value = emailAddress.value,
                        onValueChange = { emailAddress.value = it },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Color.White,
                            focusedBorderColor = Color.DarkGray,
                            unfocusedBorderColor = Color.DarkGray,
                            textColor = Color.DarkGray
                        ),
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true
                    )
                }
            }
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 10.dp,
                        start = 24.dp,
                        bottom = 5.dp,
                        end = 24.dp,
                    )
            ) {
                selectedGender=radioGroup(
                    radioOptions = languageOptions
                )
            }
        }
        item {
            Column(
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(
                        bottom = 10.dp,
                    ),
            ) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 20.dp,
                            end = 20.dp,
                        ),
                    onClick = { saveSubscriber(
                        subscriber = Subscriber(
                            idNumber.value,
                            firstName.value+" "+lastName.value,
                            mobileNumber,
                            postalCode.value,
                            "254",
                            emailAddress.value,
                            selectedGender,
                            "2021-11-12T12:38:22.172Z",
                            ""
                        ),
                        navController,context) },
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


@Composable
fun radioGroup(
    radioOptions: List<String> = listOf(),
):String{
    if (radioOptions.isNotEmpty()){
        val (selectedOption, onOptionSelected) = remember {
            mutableStateOf(radioOptions[0])
        }
        Row(
        ) {
            radioOptions.forEach { item ->

                    RadioButton(
                        selected = (item == selectedOption),
                        onClick = { onOptionSelected(item) }
                    )
                    Text(
                        text = item,
                        modifier = Modifier.padding(start = 5.dp,end = 2.dp)
                    )

            }
        }
        return selectedOption
    }else{
        return ""
    }
}

fun saveSubscriber(subscriber: Subscriber,
                   navController: NavController,
                   context: Context
){


   val apiInterface = APIClient.client!!.create(APIInterface::class.java)
   val subscriberRequest= apiInterface.createSubscriber(subscriber)
    val response=subscriberRequest?.enqueue(object : Callback<GenericResponse?> {
        override fun onResponse(call: Call<GenericResponse?>?, response: Response<GenericResponse?>) {
            if (response.code() == 200) {
                navController.navigate("finishRegistration")
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
