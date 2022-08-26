package tech.ivanpopov.bodylogger.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import tech.ivanpopov.bodylogger.R

@Composable
fun BodyLoggerBottomNavigation(navController: NavHostController) {
    BottomNavigation {
        BottomNavigationItem(
            icon = {
                Icon(
                    modifier = Modifier,
                    painter = painterResource(id = R.drawable.ic_home_icon),
                    contentDescription = ""
                )
            },
            label = { Text(stringResource(R.string.main)) },
            selected = false,
            onClick = {
                navController.navigate(Screen.Main.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = false
                    }
                    launchSingleTop = true
                }
            }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    modifier = Modifier,
                    painter = painterResource(id = R.drawable.ic_chart_icon),
                    contentDescription = ""
                )
            },
            label = { Text(stringResource(R.string.chart)) },
            selected = false,
            onClick = {
                navController.navigate(Screen.Chart.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    modifier = Modifier,
                    painter = painterResource(id = R.drawable.ic_history_icon),
                    contentDescription = ""
                )
            },
            label = { Text(stringResource(R.string.history)) },
            selected = false,
            onClick = {
                navController.navigate(Screen.History.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
    }
}
