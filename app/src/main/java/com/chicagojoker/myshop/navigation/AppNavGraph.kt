package com.chicagojoker.myshop.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.chicagojoker.myshop.presentation.cart.CartScreen
import com.chicagojoker.myshop.presentation.home.HomeScreen
import com.chicagojoker.myshop.presentation.home.ProductViewModel
import com.chicagojoker.myshop.presentation.productdetails.ProductDetailScreen
import com.chicagojoker.myshop.presentation.shopping.ShoppingScreen


@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    sharedViewModel: ProductViewModel = hiltViewModel()
) {
    NavHost(
        navController = navController,
        startDestination = BottomViewRoutes.Home.route,
        modifier = modifier
    ) {
        composable(ScreenRoutes.Home.route) {
            HomeScreen(
                onBrowseClick = {
                    navController.navigate(BottomViewRoutes.Shop.route)
                },
                onCartClick = {
                    navController.navigate(ScreenRoutes.Cart.route)
                }
            )
        }

        composable(route = ScreenRoutes.ProductDetail.route ,
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: -1
            ProductDetailScreen(productId = productId, navController = navController, viewModel = sharedViewModel)
            }

        composable(route = ScreenRoutes.Cart.route) {
            CartScreen(viewModel = sharedViewModel, navController = navController)
        }
        composable(BottomViewRoutes.Shop.route) {
            ShoppingScreen(
                viewModel = sharedViewModel,
                onProductClick = { productId ->
                    navController.navigate(ScreenRoutes.ProductDetail.createRoute(productId))
                },
                onAddToCartClick = {
                    navController.navigate(ScreenRoutes.Cart.route)
                },
                onFilterClick = {
                    // optional, or leave empty for now
                }
            )
        }

        //
//        composable(ScreenRoutes.Filter.route) {
//            FilterScreen()
//        }
    }
}