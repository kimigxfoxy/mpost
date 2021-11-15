package com.mpost.app

import android.app.DatePickerDialog
import android.content.Context
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.clickable
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
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


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
    val mYear: Int
    val mMonth: Int
    val mDay: Int
    val now = Calendar.getInstance()
    mYear = now.get(Calendar.YEAR)
    mMonth = now.get(Calendar.MONTH)
    mDay = now.get(Calendar.DAY_OF_MONTH)
    now.time = Date()
    val date = remember { mutableStateOf("") }
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            val cal = Calendar.getInstance()
            cal.set(year, month, dayOfMonth)
            date.value = getFormattedDate(cal.time, "dd MMM,yyy")
        }, mYear, mMonth, mDay
    )

    var isErrorFirstName = remember { mutableStateOf(true) }
    var isErrorFirstNameFirstInput=remember { mutableStateOf(false) }
    var isErrorLastName = remember { mutableStateOf(true) }
    var isErrorLastNameFirstInput=remember { mutableStateOf(false) }
    var isErrorIdNumber = remember { mutableStateOf(true) }
    var isErrorIdNumberFirstInput=remember { mutableStateOf(false) }
    var isErrorDateOfBirth = remember { mutableStateOf(true) }
    var isErrorDateOfBirthFirstInput=remember { mutableStateOf(false) }
    var isErrorPostalCode = remember { mutableStateOf(true) }
    var isErrorPostalCodeFirstInput=remember { mutableStateOf(false) }

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
                        onValueChange = {
                            isErrorFirstNameFirstInput.value =true
                            if(it.length<=3) firstName.value = it
                            isErrorFirstName.value = it.length<3
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

                    if (isErrorFirstName.value && isErrorFirstNameFirstInput.value) {
                        Column  {
                            Text(
                                text = "Minimum of 3 characters required",
                                color = MaterialTheme.colors.error,
                                style = MaterialTheme.typography.caption,
                            )
                        }
                    }
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
                        onValueChange = {
                            isErrorLastNameFirstInput.value =true
                            if(it.length<=3) lastName.value = it
                            isErrorLastName.value = it.length<3
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Color.White,
                            focusedBorderColor = Color.DarkGray,
                            unfocusedBorderColor = Color.DarkGray,
                            textColor = Color.DarkGray
                        ),
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true
                    )
                    if (isErrorLastName.value && isErrorLastNameFirstInput.value) {
                        Column  {
                            Text(
                                text = "Minimum of 3 characters required",
                                color = MaterialTheme.colors.error,
                                style = MaterialTheme.typography.caption,
                            )
                        }
                    }
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
                        onValueChange = {
                            isErrorIdNumberFirstInput.value =true
                            if(it.length<=8) idNumber.value = it
                            isErrorIdNumber.value = it.length < 8
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Color.White,
                            focusedBorderColor = Color.DarkGray,
                            unfocusedBorderColor = Color.DarkGray,
                            textColor = Color.DarkGray
                        ),
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true
                    )
                    if (isErrorIdNumber.value && isErrorIdNumberFirstInput.value) {
                        Column  {
                            Text(
                                text = "Minimum of 8 characters required",
                                color = MaterialTheme.colors.error,
                                style = MaterialTheme.typography.caption,
                            )
                        }
                    }
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
                        text = "Postal code",
                    )
                    OutlinedTextField(
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth(),
                        value = postalCode.value,
                        onValueChange = {
                            isErrorPostalCodeFirstInput.value =true
                            if(it.length<=5)  postalCode.value = it
                            isErrorPostalCode.value = it.length <5
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Color.White,
                            focusedBorderColor = Color.DarkGray,
                            unfocusedBorderColor = Color.DarkGray,
                            textColor = Color.DarkGray
                        ),
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true
                    )
                    if (isErrorPostalCode.value && isErrorPostalCodeFirstInput.value) {
                        Column  {
                            Text(
                                text = "Minimum of 5 characters required",
                                color = MaterialTheme.colors.error,
                                style = MaterialTheme.typography.caption,
                            )
                        }
                    }
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
                            text = "Date of birth",
                        )
                        OutlinedTextField(
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth().clickable(
                                onClick = {
                                    isErrorDateOfBirthFirstInput.value =true
                                    datePickerDialog.show()
                                    isErrorDateOfBirth.value = false
                                }),
                            value = date.value,
                            readOnly = true,
                            enabled = false,
                            onValueChange = { date.value = it },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                backgroundColor = Color.White,
                                focusedBorderColor = Color.DarkGray,
                                unfocusedBorderColor = Color.DarkGray,
                                textColor = Color.DarkGray
                            ),
                            shape = RoundedCornerShape(8.dp),
                            singleLine = true
                        )
                    if (isErrorDateOfBirth.value) {
                        Column  {
                            Text(
                                text = "Date of birth required",
                                color = MaterialTheme.colors.error,
                                style = MaterialTheme.typography.caption,
                            )
                        }
                    }
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
        if (!isErrorFirstName.value
            && !isErrorLastName.value
            && !isErrorIdNumber.value
            && !isErrorPostalCode.value
            && !isErrorDateOfBirth.value
        ) {
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
                        onClick = {
                            saveSubscriber(
                                subscriber = Subscriber(
                                    idNumber.value,
                                    firstName.value + " " + lastName.value,
                                    mobileNumber,
                                    postalCode.value,
                                    "254",
                                    emailAddress.value,
                                    selectedGender,
                                    formatSubmitDateDate(date.value),
                                    ""
                                ),
                                navController, context
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

    subscriber.dob?.let { Log.i("SubscriberDate: ", it) }

   val apiInterface = APIClient.client!!.create(APIInterface::class.java)
   val subscriberRequest= apiInterface.createSubscriber(subscriber)
//    val response=subscriberRequest?.enqueue(object : Callback<GenericResponse?> {
//        override fun onResponse(call: Call<GenericResponse?>?, response: Response<GenericResponse?>) {
//            if (response.code() == 200) {
                navController.navigate("finishRegistration/"+subscriber.mobileNumber)
//            }
//        }
//
//        override fun onFailure(call: Call<GenericResponse?>?, t: Throwable?) {
//            Toast.makeText(
//                context,
//                "Network or phone number error",
//                Toast.LENGTH_SHORT
//            ).show()
//        }
//    })

}

fun getFormattedDate(date: Date?, format: String): String {
    try {
        if (date != null) {
            val formatter = SimpleDateFormat(format, Locale.getDefault())
            return formatter.format(date)
        }
    } catch (e: Exception) {

    }
    return ""
}

fun formatSubmitDateDate(date:String):String{
    var sf= SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    return sf.format(
        SimpleDateFormat("dd MMM,yyy")
            .parse(date)
    )

}
