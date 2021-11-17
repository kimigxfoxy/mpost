package com.mpost.app

import android.graphics.Color as androidColor
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mpost.app.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            AppTheme {
                Scaffold {
                    ToolbarWidget()
                    NavigationComponent(navController)
                }
            }
        }
    }
}
var buttonPrimaryColor= Color(androidColor.parseColor("#187BB2"))

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppTheme {
        Greeting("Android")
    }
}


@Composable
fun NavigationComponent(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
//            homeScreen(navController)
            paymentInitiated(navController,"254713844953")

        }
        composable("enterPhoneNumber") {
            detailPhoneNumber(navController)
        }
        composable(
            "enterOTP/{mobileNumber}",
            arguments = listOf(navArgument("mobileNumber") { type = NavType.StringType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("mobileNumber")?.let {
                detailOTP(
                    navController,
                    it
                )
            }
        }

        composable(
            "enterPersonal/{mobileNumber}",
            arguments = listOf(navArgument("mobileNumber") { type = NavType.StringType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("mobileNumber")
                ?.let { detailPersonal(navController, it) }
        }

        composable("finishRegistration/{mobileNumber}",
            arguments = listOf(navArgument("mobileNumber") { type = NavType.StringType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("mobileNumber")
                ?.let { finishRegistration(navController, it) }
        }

        composable("activateRegistration/{mobileNumber}",
            arguments = listOf(navArgument("mobileNumber") { type = NavType.StringType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("mobileNumber")?.let {
                activateRegistration(navController, it)
            }
        }

        composable("paymentInitiated/{mobileNumber}",
            arguments = listOf(navArgument("mobileNumber") { type = NavType.StringType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("mobileNumber")?.let {
                paymentInitiated(navController, it)
            }
        }

        composable("profile/{names}/{mobileNumber}/{city}/{area}/{postalCode}/{expiryDate}/{isPaid}/{isExpired}",
            arguments = listOf(
                navArgument("names") { type = NavType.StringType },
                navArgument("mobileNumber") { type = NavType.StringType },
                navArgument("city") { type = NavType.StringType },
                navArgument("area") { type = NavType.StringType },
                navArgument("postalCode") { type = NavType.StringType },
                navArgument("expiryDate") { type = NavType.StringType },
                navArgument("isPaid") { type = NavType.StringType },
                navArgument("isExpired") { type = NavType.StringType },

            )
        ) { backStackEntry ->
            val names=backStackEntry.arguments?.getString("names")
            val mobileNumber=backStackEntry.arguments?.getString("mobileNumber")
            val city=backStackEntry.arguments?.getString("city")
            val area=backStackEntry.arguments?.getString("area")
            val postalCode=backStackEntry.arguments?.getString("postalCode")
            val expiryDate=backStackEntry.arguments?.getString("expiryDate")
            val isPaid=backStackEntry.arguments?.getString("isPaid")
            val isExpired=backStackEntry.arguments?.getString("isExpired")

            profilePage(
                navController,
                names!!,
                mobileNumber!!,
                city!!,
                area!!,
                postalCode!!,
                expiryDate!!,
                isPaid!!,
                isExpired!!,
            )

        }
    }
}

