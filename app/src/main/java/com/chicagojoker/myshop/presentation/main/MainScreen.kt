package com.chicagojoker.myshop.presentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.chicagojoker.myshop.navigation.AppNavGraph
import com.chicagojoker.myshop.presentation.bottombar.BottomBar

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.weight(1f)) {
            AppNavGraph(navController = navController)
        }
        BottomBar(navController = navController)
    }
}