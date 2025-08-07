package com.chicagojoker.myshop.navigation

sealed class ScreenRoutes (val route: String){
    object Home : ScreenRoutes("home")
    object ProductDetail : ScreenRoutes("product_detail/{productId}") {
        fun createRoute(productId: Int) = "product_detail/$productId"
    }
    object Cart : ScreenRoutes("cart")
    object Categories : ScreenRoutes("categories")
    object Filter : ScreenRoutes("filter")
}