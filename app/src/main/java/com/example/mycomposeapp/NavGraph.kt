package com.example.mycomposeapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.mycomposeapp.ui.ArgumentKeys
import com.example.mycomposeapp.ui.Screen
import com.example.mycomposeapp.ui.catalog.category.CategoryScreen
import com.example.mycomposeapp.ui.catalog.product.ProductDetailScreen
import com.example.mycomposeapp.ui.checkout.CheckoutScreen
import com.example.mycomposeapp.ui.checkout.CheckoutSuccessScreen
import com.example.mycomposeapp.ui.customer.Address
import com.example.mycomposeapp.ui.customer.AddressDetailScreen
import com.example.mycomposeapp.ui.customer.AddressNavType
import com.example.mycomposeapp.ui.customer.AddressNavigation
import com.example.mycomposeapp.ui.customer.AddressScreen
import com.example.mycomposeapp.ui.customer.CustomerInfoScreen
import com.example.mycomposeapp.ui.customer.MyAccountScreen
import com.example.mycomposeapp.ui.getAddressDetailRoute
import com.example.mycomposeapp.ui.getCheckoutRoute
import com.example.mycomposeapp.ui.getProductDetailRoute
import com.example.mycomposeapp.ui.home.HomeScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
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

        composable(route = Screen.Category.route) {
            CategoryScreen { productId ->
                navController.navigate(getProductDetailRoute(productId))
            }
        }

        // route
        // NamedNavArgument : a quick way to create new one is using navArgument
        composable(
            route = Screen.ProductDetail.route,
            arguments = listOf(navArgument(ArgumentKeys.PRODUCT_ID) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString(ArgumentKeys.PRODUCT_ID)
            ProductDetailScreen(productId = productId ?: "") { cartId, customerId ->
                navController.navigate(getCheckoutRoute(cartId, customerId))
            }
        }

        navigation(route = Screen.Customer.route, startDestination = Screen.MyAccount.route) {
            composable(route = Screen.MyAccount.route) {
                MyAccountScreen(
                    navController = navController,
                    openAddressScreen = { addressId ->
                        navController.navigate(getAddressDetailRoute(addressId))
                    })
            }

            composable(
                route = Screen.AddressDetail.route,
                arguments = listOf(navArgument(ArgumentKeys.ADDRESS_ID) {
                    nullable = true
                })
            ) { backStackEntry ->
                val addressId = backStackEntry.arguments?.getString(ArgumentKeys.ADDRESS_ID)
                AddressDetailScreen(addressId = addressId) {
                    navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.set(ArgumentKeys.NEW_ADDRESS_ID, it)
                    navController.popBackStack()
                }
            }

            composable(route = Screen.CustomerInfo.route) {
                CustomerInfoScreen {
                    navController.popBackStack()
                }
            }

        }

        composable(
            route = Screen.Checkout.route,
            arguments = listOf(
                navArgument(ArgumentKeys.CART_ID) { type = NavType.StringType },
                navArgument(ArgumentKeys.CUSTOMER_ID) { type = NavType.StringType })
        ) { backStackEntry ->
            val cartId = backStackEntry.arguments?.getString(ArgumentKeys.CART_ID)
            val customerId = backStackEntry.arguments?.getString(ArgumentKeys.CUSTOMER_ID)
            CheckoutScreen(cartId = cartId ?: "", customerId = customerId ?: "") {
                navController.navigate(Screen.CheckoutSuccess.route)
            }
        }

        composable(route = Screen.CheckoutSuccess.route) {
            CheckoutSuccessScreen(goHomeAction = {
                navController.popBackStack(
                    route = Screen.Home.route,
                    inclusive = false,
                    saveState = true
                )
            }, viewOrderDetailAction = {

            })
        }

    }
}
