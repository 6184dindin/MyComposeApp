package com.example.mycomposeapp

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.mycomposeapp.screen.Screen
import com.example.mycomposeapp.screen.ScreenA
import com.example.mycomposeapp.screen.ScreenB
import com.example.mycomposeapp.screen.ScreenC
import com.example.mycomposeapp.ui.catalog.category.CATEGORY_SCREEN_ROUTE
import com.example.mycomposeapp.ui.catalog.category.CategoryScreen
import com.example.mycomposeapp.ui.catalog.category.getOpenCategoryScreenRoute
import com.example.mycomposeapp.ui.catalog.product.ARG_PRODUCT_ID
import com.example.mycomposeapp.ui.catalog.product.PRODUCT_DETAIL_SCREEN_ROUTE
import com.example.mycomposeapp.ui.catalog.product.ProductDetailScreen
import com.example.mycomposeapp.ui.catalog.product.getProductDetailRoute
import com.example.mycomposeapp.ui.checkout.ARG_CART_ID
import com.example.mycomposeapp.ui.checkout.ARG_CUSTOMER_ID
import com.example.mycomposeapp.ui.checkout.CHECKOUT_SCREEN_ROUTE
import com.example.mycomposeapp.ui.checkout.CHECKOUT_SUCCESS_SCREEN_ROUTE
import com.example.mycomposeapp.ui.checkout.CheckoutScreen
import com.example.mycomposeapp.ui.checkout.CheckoutSuccessScreen
import com.example.mycomposeapp.ui.checkout.getCheckoutRoute
import com.example.mycomposeapp.ui.customer.ADDRESS_DETAIL_SCREEN_ROUTE
import com.example.mycomposeapp.ui.customer.ARG_ADDRESS_ID
import com.example.mycomposeapp.ui.customer.ARG_NEW_ADDRESS_ID
import com.example.mycomposeapp.ui.customer.Address
import com.example.mycomposeapp.ui.customer.AddressDetailScreen
import com.example.mycomposeapp.ui.customer.AddressNavType
import com.example.mycomposeapp.ui.customer.AddressNavigation
import com.example.mycomposeapp.ui.customer.AddressScreen
import com.example.mycomposeapp.ui.customer.CUSTOMER_INFO_SCREEN_ROUTE
import com.example.mycomposeapp.ui.customer.CUSTOMER_ROUTE
import com.example.mycomposeapp.ui.customer.CustomerInfoScreen
import com.example.mycomposeapp.ui.customer.MY_ACCOUNT_SCREEN_ROUTE
import com.example.mycomposeapp.ui.customer.MyAccountScreen
import com.example.mycomposeapp.ui.customer.getAddressDetailRoute
import com.example.mycomposeapp.ui.home.HOME_SCREEN_ROUTE
import com.example.mycomposeapp.ui.home.HomeScreen
import com.example.mycomposeapp.ui.theme.MyComposeAppTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            val navController = rememberNavController()
//            MyNavGraph(navController)

            MyComposeAppTheme {
                Box(modifier = Modifier.fillMaxSize()) {
                    FeatureThatRequiresCameraPermission()
                }
            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun FeatureThatRequiresCameraPermission() {
    // Camera permission state
    val cameraPermissionState = rememberPermissionState(
        android.Manifest.permission.CAMERA
    )
    if (cameraPermissionState.status.isGranted) {
        Text("Camera permission Granted")
    } else {
        Column {
            val textToShow = if (cameraPermissionState.status.shouldShowRationale) {
                // If the user has denied the permission but the rationale can be shown, then gently explain
                // why the app requires this permission
                "The camera is important for this app. Please grant the permission."
            } else {
                // If it's the first time the user lands on this feature, or the user doesn't want to be asked again
                // for this permission, explain that the permission is required
                "Camera permission required for this feature to be available. " + "Please grant the permission"
            }
            Text(textToShow)
            Button(onClick = { cameraPermissionState.launchPermissionRequest() }) {
                Text("Request permission")
            }
        }
    }
}
@Composable
fun ExampleRequiresCameraPermission() {
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Permission Accepted: Do something
            Log.e("ExampleScreen","PERMISSION GRANTED")

        } else {
            // Permission Denied: Do something
            Log.e("ExampleScreen","PERMISSION DENIED")
        }
    }
    val context = LocalContext.current

    Button(
        onClick = {
            // Check permission
            when (PackageManager.PERMISSION_GRANTED) {
                ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA) -> {
                    // Some works that require permission
                    Log.e("ExampleScreen","Code requires permission")
                }
                else -> {
                    // Asking for permission
                    launcher.launch(android.Manifest.permission.CAMERA)
                }
            }
        }
    ) {
        Text(text = "Check and Request Permission")
    }
}

@Composable
fun MyNavGraph(navController: NavHostController) {
    MyComposeAppTheme {
        NavHost(navController = navController, startDestination = Screen.A.route) {
            composable(route = Screen.A.route) {
                ScreenA(navController)
            }
            composable(route = Screen.B.route) {
                ScreenB(navController)
            }
            composable(route = Screen.C.route) {
                val userName = it.arguments?.getString("userName")
                ScreenC(navController, userName ?: "null")
            }
            composable(route = HOME_SCREEN_ROUTE) {
                HomeScreen(
                    openCategoryAction = {
                        navController.navigate(getOpenCategoryScreenRoute())
                    },
                    openMyAccountScreen = {
                        navController.navigate(MY_ACCOUNT_SCREEN_ROUTE)
                    },
                    editCustomerInfo = {
                        navController.navigate(CUSTOMER_ROUTE)
                    },
                    openAddressList = {
                        val address = Address(id = 1, street = "Phạm Hùng", city = "Hà Nội")
                        navController.navigate(AddressNavigation.createRoute(address))
                    }
                )
            }

            composable(route = AddressNavigation.route, arguments = listOf(
                navArgument(AddressNavigation.addressArg) {
                    nullable = true
                    type = AddressNavType()
                }
            )) {
                val address = AddressNavigation.fromNav(it)
                AddressScreen(addresses = listOf(address)) {
                    navController.popBackStack()
                }
            }

            composable(route = CATEGORY_SCREEN_ROUTE) {
                CategoryScreen { productId ->
                    navController.navigate(getProductDetailRoute(productId))
                }
            }

            // route
            // NamedNavArgument : a quick way to create new one is using navArgument
            composable(
                route = PRODUCT_DETAIL_SCREEN_ROUTE,
                arguments = listOf(navArgument(ARG_PRODUCT_ID) {
                    type = NavType.StringType
                })
            ) { backStackEntry ->
                val productId = backStackEntry.arguments?.getString(ARG_PRODUCT_ID)
                ProductDetailScreen(productId = productId ?: "") { cartId, customerId ->
                    navController.navigate(getCheckoutRoute(cartId, customerId))
                }
            }

            navigation(route = CUSTOMER_ROUTE, startDestination = MY_ACCOUNT_SCREEN_ROUTE) {
                composable(route = MY_ACCOUNT_SCREEN_ROUTE) {
                    MyAccountScreen(
                        navController = navController,
                        openAddressScreen = { addressId ->
                            navController.navigate(getAddressDetailRoute(addressId))
                        })
                }

                composable(
                    route = ADDRESS_DETAIL_SCREEN_ROUTE,
                    arguments = listOf(navArgument(ARG_ADDRESS_ID) {
                        nullable = true
                    })
                ) { backStackEntry ->
                    val addressId = backStackEntry.arguments?.getString(ARG_ADDRESS_ID)
                    AddressDetailScreen(addressId = addressId) {
                        navController.previousBackStackEntry
                            ?.savedStateHandle
                            ?.set(ARG_NEW_ADDRESS_ID, it)
                        navController.popBackStack()
                    }
                }

                composable(route = CUSTOMER_INFO_SCREEN_ROUTE) {
                    CustomerInfoScreen {
                        navController.popBackStack()
                    }
                }

            }

            composable(
                route = CHECKOUT_SCREEN_ROUTE,
                arguments = listOf(
                    navArgument(ARG_CART_ID) { type = NavType.StringType },
                    navArgument(ARG_CUSTOMER_ID) { type = NavType.StringType })
            ) { backStackEntry ->
                val cartId = backStackEntry.arguments?.getString(ARG_CART_ID)
                val customerId = backStackEntry.arguments?.getString(ARG_CUSTOMER_ID)
                CheckoutScreen(cartId = cartId ?: "", customerId = customerId ?: "") {
                    navController.navigate(CHECKOUT_SUCCESS_SCREEN_ROUTE)
                }
            }

            composable(route = CHECKOUT_SUCCESS_SCREEN_ROUTE) {
                CheckoutSuccessScreen(goHomeAction = {
                    navController.popBackStack(
                        route = HOME_SCREEN_ROUTE,
                        inclusive = false,
                        saveState = true
                    )
                }, viewOrderDetailAction = {

                })
            }

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TestPreview() {
    MyComposeAppTheme {
    }
}