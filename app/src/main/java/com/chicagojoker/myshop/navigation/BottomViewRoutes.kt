package com.chicagojoker.myshop.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomViewRoutes (
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    object Home : BottomViewRoutes("home", "Home", Icons.Default.Home)
    object Shop : BottomViewRoutes("shop", "Shop", Icons.Default.ShoppingCart)
    object MyList : BottomViewRoutes("myList", "My List", Icons.AutoMirrored.Filled.List)

}