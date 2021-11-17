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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavController
import com.mpost.app.pojo.AreaCode
import com.mpost.app.pojo.City
import com.mpost.app.pojo.GenericResponse
import com.mpost.app.pojo.Subscriber
import com.mpost.app.retrofit.APIClient
import com.mpost.app.retrofit.APIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


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
                             firstName.value = it
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
                             lastName.value = it
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
            ){
                Column (
                    modifier = Modifier.weight(1f).padding(end =2.dp)
                        ){
                    Text(
                        text = "City",
                    )
                    TextFieldWithDropdown(
                        modifier = Modifier.fillMaxWidth(),
                        value = textFieldValueCity.value,
                        setValue = ::onValueChangedCity,
                        onDismissRequest = ::onDropdownDismissRequestCity,
                        dropDownExpanded = dropDownExpandedCity.value,
                        list = dropDownOptionsCity.value,
                        label = ""
                    )
                }
                Column (
                    modifier = Modifier.weight(1f).padding(start =2.dp)
                        ){
                    Text(
                        text = "Area",
                    )
                    TextFieldWithDropdown(
                        modifier = Modifier.fillMaxWidth(),
                        value = textFieldValueArea.value,
                        setValue = {onValueChangedArea(it,postalCode,isErrorPostalCode)},
                        onDismissRequest = ::onDropdownDismissRequestArea,
                        dropDownExpanded = dropDownExpandedArea.value,
                        list = dropDownOptionsArea.value,
                        label = ""
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
                        text = "Postal code",
                    )
                    OutlinedTextField(
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth(),
                        value = postalCode.value,
                        onValueChange = {
                               postalCode.value = it
                               isErrorPostalCode.value = false
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Color.White,
                            focusedBorderColor = Color.DarkGray,
                            unfocusedBorderColor = Color.DarkGray,
                            textColor = Color.DarkGray
                        ),
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true,
                        readOnly = true
                    )
                    if (isErrorPostalCode.value){
                        Column  {
                            Text(
                                text = "Postal code is required",
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
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(
                                    onClick = {
                                        isErrorDateOfBirthFirstInput.value = true
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
        item{

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
                navController.navigate("finishRegistration/"+subscriber.mobileNumber)
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


@Composable
fun TextFieldWithDropdown(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    setValue: (TextFieldValue) -> Unit,
    onDismissRequest: () -> Unit,
    dropDownExpanded: Boolean,
    list: List<String>,
    label: String = "",
) {
    Box(modifier) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    if (!focusState.isFocused)
                        onDismissRequest()
                },
            value = value,
            onValueChange = setValue,
            label = { Text(label) },
            colors = TextFieldDefaults.outlinedTextFieldColors()
        )
        DropdownMenu(
            expanded = dropDownExpanded,
            properties = PopupProperties(
                focusable = false,
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            ),
            onDismissRequest = onDismissRequest
        ) {
            list.forEach { text ->
                DropdownMenuItem(
                    onClick = {
                        setValue(
                            TextFieldValue(
                                text,
                                TextRange(text.length)
                            )
                        )
                        onDismissRequest()
                    }) {
                    Text(text = text)
                }
            }
        }
    }
}

val dropDownOptionsCity = mutableStateOf(listOf<String>())
val textFieldValueCity = mutableStateOf(TextFieldValue())
val dropDownExpandedCity = mutableStateOf(false)

fun onDropdownDismissRequestCity() {
    dropDownExpandedCity.value = false
}

fun onValueChangedCity(value: TextFieldValue) {
    textFieldValueCity.value = value
    getCities(value)
}

var selectedCityCode=""
fun getCities(textFieldValue1: TextFieldValue): List<City>{

    var cities=ArrayList<City>()
    val apiInterface = APIClient.client!!.create(APIInterface::class.java)
    val subscriberRequest= apiInterface.getCitiesByName("254",textFieldValue1.text)
    val response=subscriberRequest?.enqueue(object : Callback<List<City>?> {
        override fun onResponse(call: Call<List<City>?>?, response: Response<List<City>?>) {
            if (response.code() == 200) {
                cities= (response.body() as ArrayList<City>?)!!
                var cityStrings=ArrayList<String>()
                for(city in cities){
                    city.cityName?.let { cityStrings.add(it) }
                }
                dropDownExpandedCity.value = true
                dropDownOptionsCity.value = cityStrings
                if(cities.size==1){
                    selectedCityCode=cities.get(0).cityShortName.toString()
                }else{
                    selectedCityCode=""
                }
                Log.i("cityCode",selectedCityCode)
            }
        }

        override fun onFailure(call: Call<List<City>?>?, t: Throwable?) {

        }
    })
    return cities
}


val dropDownOptionsArea = mutableStateOf(listOf<String>())
val textFieldValueArea = mutableStateOf(TextFieldValue())
val dropDownExpandedArea = mutableStateOf(false)

fun onDropdownDismissRequestArea() {
    dropDownExpandedArea.value = false
}

fun onValueChangedArea(value: TextFieldValue,
                       postalCode:MutableState<String>,
                       isErrorPostalCode:MutableState<Boolean>
) {
    dropDownExpandedArea.value = true
    textFieldValueArea.value = value

    getCityPostalCodes(
        selectedCityCode,
        value,
        postalCode,
        isErrorPostalCode
    )
}

var selectedAreaCode=""
fun getCityPostalCodes(cityCode: String, textFieldValue1: TextFieldValue,
                       postalCode:MutableState<String>,
                       isErrorPostalCode:MutableState<Boolean>
): List<AreaCode>{
    var areas=ArrayList<AreaCode>()
    val apiInterface = APIClient.client!!.create(APIInterface::class.java)
    val subscriberRequest= apiInterface.getPostalCodeByCityCodeAndSearchParam(cityCode,textFieldValue1.text)
    val response=subscriberRequest?.enqueue(object : Callback<List<AreaCode>?> {
        override fun onResponse(call: Call<List<AreaCode>?>?, response: Response<List<AreaCode>?>) {
            if (response.code() == 200) {
                areas= (response.body() as ArrayList<AreaCode>?)!!
                var areaStrings=ArrayList<String>()
                for(areas in areas){
                    areas.postalName?.let { areaStrings.add(it) }
                }
                dropDownExpandedArea.value = true
                dropDownOptionsArea.value = areaStrings
                if(areas.size==1){
                    selectedAreaCode=areas.get(0).postalCode.toString()
                    isErrorPostalCode.value=false
                }else{
                    selectedAreaCode=""
                }
                postalCode.value=selectedAreaCode
            }
        }

        override fun onFailure(call: Call<List<AreaCode>?>?, t: Throwable?) {

        }
    })
    return areas
}