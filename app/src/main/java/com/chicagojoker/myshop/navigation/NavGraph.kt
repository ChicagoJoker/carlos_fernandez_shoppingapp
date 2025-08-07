package com.chicagojoker.myshop.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.chicagojoker.myshop.presentation.cart.CartScreen
import com.chicagojoker.myshop.presentation.home.HomeScreen
import com.chicagojoker.myshop.presentation.home.ProductViewModel
import com.chicagojoker.myshop.presentation.productdetails.ProductDetailScreen


@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    sharedViewModel: ProductViewModel = hiltViewModel()
) {
    NavHost(
        navController = navController,
        startDestination = ScreenRoutes.Home.route,
        modifier = modifier
    ) {
        composable(ScreenRoutes.Home.route) {
            HomeScreen(
                onProductClick = { productId ->
                    navController.navigate(ScreenRoutes.ProductDetail.createRoute(productId))
                },
                onFilterClick = {
                    navController.navigate(ScreenRoutes.Filter.route)
                },
                onAddToCartClick = {
                    navController.navigate(ScreenRoutes.Cart.route)
                },
                viewModel = sharedViewModel
            )
        }

        composable(route = ScreenRoutes.ProductDetail.route ,
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: -1
            ProductDetailScreen(productId = productId, navController = navController, viewModel = sharedViewModel)
            }

        composable(route = ScreenRoutes.Cart.route) {
            CartScreen(viewModel = sharedViewModel)
        }

        //
//        composable(ScreenRoutes.Filter.route) {
//            FilterScreen()
//        }
    }
}