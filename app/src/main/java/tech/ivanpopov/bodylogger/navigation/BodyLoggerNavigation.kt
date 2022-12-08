package tech.ivanpopov.bodylogger.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import tech.ivanpopov.bodylogger.MainViewModel
import tech.ivanpopov.bodylogger.ui.ChartScreen
import tech.ivanpopov.bodylogger.ui.HistoryScreen
import tech.ivanpopov.bodylogger.ui.MainScreen

@Composable
fun BodyLoggerNavigation(
    navController: NavHostController = rememberNavController(),
    viewModel: MainViewModel,
) {
    NavHost(navController = navController, startDestination = Screen.Main.route) {
        composable(Screen.Main.route) {
            MainScreen(viewModel)
        }
        composable(Screen.Chart.route) {
            ChartScreen(viewModel)
        }
        composable(Screen.History.route) {
            HistoryScreen(viewModel)
        }
    }
}
