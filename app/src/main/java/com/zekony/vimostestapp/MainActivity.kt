package com.zekony.vimostestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.zekony.feature.authentication.REGISTRATION_ROUTE
import com.zekony.feature.authentication.registrationEntry
import com.zekony.feature.productinfo.PRODUCT_INFO_ENTRY
import com.zekony.feature.productinfo.productInfoEntry
import com.zekony.feature.products.productsEntry
import com.zekony.vimostestapp.navigation.NavigationBottomBar
import com.zekony.vimostestapp.ui.theme.VimosTestAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            VimosTestAppTheme {
                Scaffold(
                    bottomBar = {
                        NavigationBottomBar(
                            navController
                        )
                    }
                ) { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = REGISTRATION_ROUTE,
                        enterTransition = { fadeIn() },
                        exitTransition = { fadeOut() },
                        popEnterTransition = { fadeIn() },
                        popExitTransition = { fadeOut() },
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        registrationEntry()
                        productsEntry(
                            navigateToInfoScreen = {
                                navController.navigate("${PRODUCT_INFO_ENTRY}/$it")
                            }
                        )
                        productInfoEntry()
                    }
                }
            }
        }
    }
}