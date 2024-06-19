package com.zekony.vimostestapp.navigation

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.zekony.feature.authentication.REGISTRATION_ROUTE
import com.zekony.feature.products.PRODUCTS_ROUTE

@Composable
fun NavigationBottomBar(navController: NavController) {
    BottomAppBar {
        NavigationButtons.values().forEach {
            val currentDestination =
                navController.currentBackStackEntryAsState().value?.destination?.route
            val buttonColor =
                if (currentDestination == it.route) MaterialTheme.colorScheme.primary else Color.DarkGray
            val interactionSource = MutableInteractionSource()
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable(
                        interactionSource,
                        indication = null
                    ) {
                        navController.navigate(it.route)
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = it.navigationIcon,
                    contentDescription = null,
                    tint = buttonColor,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = stringResource(id = it.screenName),
                    style = MaterialTheme.typography.bodySmall,
                    color = buttonColor
                )
            }
        }
    }
}

enum class NavigationButtons(
    val route: String,
    val navigationIcon: ImageVector,
    @StringRes val screenName: Int
) {
    Registration(
        REGISTRATION_ROUTE,
        Icons.Default.AccountCircle,
        com.zekony.resources.R.string.profile_bottombar_name
    ),
    Products(
        PRODUCTS_ROUTE,
        Icons.AutoMirrored.Default.List,
        com.zekony.resources.R.string.products_bottombar_name
    )
}