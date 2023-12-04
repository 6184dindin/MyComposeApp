package com.example.mycomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeAppTheme {
                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Column(Modifier.padding(16.dp)) {
//
//                        // Biến đếm số lần click
//                        var clickCount by remember { mutableStateOf(0) }
//
//                        Button(onClick = {
//                            clickCount++
//                        }) {
//                            Text("Recompose MyScreen")
//                        }
//
//                        HomeScreenWithViewModel(clickCount)
//
//                    }
//                }
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    val navController = rememberNavController()
    MyComposeAppTheme {
        NavHost(navController = navController, startDestination = HOME_SCREEN_ROUTE) {
            composable(route = HOME_SCREEN_ROUTE) {
                HomeScreen(
                    openCategoryAction = {
//                        navController.navigate(getOpenCategoryScreenRoute())
                        navController.navigate("HomeScreenWithViewModel2?name=John&age=18")
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


            composable("HomeScreenWithViewModel2?name={name}&age={age}",
            arguments = listOf(
                navArgument("name"){
                    type = NavType.StringType
                    defaultValue = "Default"
                },
                navArgument("age") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
            ) {
                HomeScreenWithViewModel2() {
                    navController.navigate(MY_ACCOUNT_SCREEN_ROUTE)
                }
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
//        TestScreen()
    }
}