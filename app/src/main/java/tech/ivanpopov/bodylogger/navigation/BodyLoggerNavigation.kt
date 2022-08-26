package tech.ivanpopov.bodylogger.navigation

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import tech.ivanpopov.bodylogger.ui.chart.ChartScreen
import tech.ivanpopov.bodylogger.ui.history.HistoryScreen
import tech.ivanpopov.bodylogger.ui.main.MainScreen

@Composable
fun BodyLoggerNavigation(
    navController: NavHostController = rememberNavController(),
    scaffoldState: ScaffoldState
) {
    val scope = rememberCoroutineScope()
    NavHost(navController = navController, startDestination = Screen.Main.route) {
        composable(Screen.Main.route) {
            MainScreen()
        }
        composable(Screen.Chart.route) {
            ChartScreen()
        }
        composable(Screen.History.route) {
            HistoryScreen()
        }
    }
}
