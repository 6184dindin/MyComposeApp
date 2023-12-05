package com.example.mycomposeapp.ui

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Category : Screen("category")
    object ProductDetail : Screen("product?productId={product_id}")
    object MyAccount : Screen("my_account")
    object Customer : Screen("customer")
    object CustomerInfo : Screen("customer_info")
    object AddressDetail : Screen("address_detail?addressId={address_id}")
    object Checkout : Screen("checkout/{cart_id}/{customer_id}")
    object CheckoutSuccess : Screen("checkout_success")
}

object ArgumentKeys {
    const val PRODUCT_ID = "product_id"
    const val CART_ID = "cart_id"
    const val CUSTOMER_ID = "customer_id"
    const val ADDRESS_ID = "address_id"
    const val NEW_ADDRESS_ID = "new_address_id"
}

fun getProductDetailRoute(productId: String?): String {
    return if (productId != null)
        "product?productId=$productId"
    else
        "product"
}

fun getAddressDetailRoute(addressId: String?): String {
    return if (addressId != null) "address_detail?addressId=$addressId" else "address_detail"
}

fun getCheckoutRoute(cartId: String, customerId: String): String {
    return "checkout/$cartId/$customerId"
}