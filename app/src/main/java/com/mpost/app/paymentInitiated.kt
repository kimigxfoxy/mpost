package com.mpost.app

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mpost.app.pojo.SubscriberDetails
import com.mpost.app.retrofit.APIClient
import com.mpost.app.retrofit.APIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun paymentInitiated(
    navController: NavController,
    mobileNumber:String
) {

    val context = LocalContext.current

    Column {

        var buttonPrimaryColor= Color(android.graphics.Color.parseColor("#1774A7"))
        var buttonPrimaryColor2= Color(android.graphics.Color.parseColor("#D9EEFA"))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            content = {

                Card(elevation = 4.dp, modifier = Modifier
                    .padding(top = 100.dp, start = 20.dp,end = 20.dp)
                    .fillMaxWidth()) {
                    Column() {
                        Text("Pending payment", color = Color.Gray,
                            modifier = Modifier.padding(start=25.dp,top=10.dp,bottom = 10.dp)
                        )
                        Divider(
                            color = Color.LightGray,
                            thickness = 2.dp,
                        )
                        Row(modifier = Modifier
                            .padding(all = 10.dp)
                            .fillMaxWidth()) {

                            Text("A push notification for payment Ksh 300 to activate your virtual box has ben sent to mobile number ${mobileNumber}. Once paid press the refresh button below",
                                modifier = Modifier.padding(start=25.dp,top=10.dp,bottom = 10.dp)
                            )
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
                                    Button(
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor = buttonPrimaryColor,
                                            contentColor = Color.White
                                        ),
                                        onClick = {
                                            getSubscriberDetailsActivatePage(
                                            mobileNumber,context,navController)
                                        }
                                    ){
                                        Text(text = "REFRESH")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        )
    }
}


fun getSubscriberDetailsActivatePage(mobileNumber: String, context: Context, navController: NavController) {
    val apiInterface = APIClient.client!!.create(APIInterface::class.java)
    val subscriberRequest = apiInterface.getSubscriberDetails(mobileNumber)
    val response = subscriberRequest?.enqueue(object : Callback<SubscriberDetails?> {
        override fun onResponse(
            call: Call<SubscriberDetails?>?,
            response: Response<SubscriberDetails?>
        ) {
            Log.i("isExpired", response.body()?.isExpired.toString())
            Log.i("isPaid", response.body()?.isPaid.toString())

            if (response.code()== 200) {
                val isExpired=response.body()?.isExpired.toString()
                val isPaid=response.body()?.isPaid.toString()
                val idNumber=response.body()?.idNumber.toString()
                val city=response.body()?.city.toString()
                val postalCode=response.body()?.postalCode.toString()
                val expiryDate=response.body()?.expiry.toString()
                val names=response.body()?.names.toString()

                if(isExpired=="false" && isPaid=="true") {
                    var route =
                        "profile/${names}/${mobileNumber}/${city}/${city}/${postalCode}/${expiryDate}/${isPaid}/${isExpired}"
                    navController.navigate(route)
                }
            }else{
                Toast.makeText(
                    context,
                    "Network error",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

        override fun onFailure(call: Call<SubscriberDetails?>?, t: Throwable?) {
            Toast.makeText(
                context,
                "Network error",
                Toast.LENGTH_SHORT
            ).show()
        }
    })
}