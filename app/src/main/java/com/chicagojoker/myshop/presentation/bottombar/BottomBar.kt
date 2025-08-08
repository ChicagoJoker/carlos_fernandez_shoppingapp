package com.chicagojoker.myshop.presentation.bottombar

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.getValue
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.chicagojoker.myshop.navigation.BottomViewRoutes

@Composable
fun BottomBar(navController: NavHostController) {
    val items = listOf(
        BottomViewRoutes.Home,
        BottomViewRoutes.Shop,
        BottomViewRoutes.MyList
    )

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
//                    navController.navigate(item.route) {
//                        // Avoid multiple copies of same destination
////                        popUpTo(navController.graph.startDestinationId) {
////                            inclusive = false
////                        }
////                        launchSingleTop = true
//                        popUpTo(navController.graph.findStartDestination().id) {
//                            saveState = true
//                            inclusive = false
//                        }
//                        launchSingleTop = true
//                        restoreState = true
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().route ?: BottomViewRoutes.Home.route) {
                                inclusive = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = { Icon(imageVector = item.icon, contentDescription = item.label) },
                label = { Text(item.label) }
            )
        }
    }
}
