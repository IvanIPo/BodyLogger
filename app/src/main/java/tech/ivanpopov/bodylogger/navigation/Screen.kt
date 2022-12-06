package tech.ivanpopov.bodylogger.navigation

/**
 * Represent all Screens (Composables) in the app.
 */
sealed class Screen(
    val route: String
) {
    object Main : Screen("main")
    object Chart : Screen("chart")
    object History : Screen("history")
}
