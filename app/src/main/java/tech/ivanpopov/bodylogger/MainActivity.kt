package tech.ivanpopov.bodylogger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import tech.ivanpopov.bodylogger.navigation.BodyLoggerBottomNavigation
import tech.ivanpopov.bodylogger.navigation.BodyLoggerNavigation
import tech.ivanpopov.bodylogger.theme.BodyLoggerTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BodyLoggerTheme {
                val scaffoldState = rememberScaffoldState()
                val navController = rememberNavController()
                Scaffold(scaffoldState = scaffoldState,
                    topBar = {
                        TopAppBar(title = { Text(stringResource(R.string.app_name)) })
                    },
                    bottomBar = { BodyLoggerBottomNavigation(navController = navController) },
                    snackbarHost = {
                        SnackbarHost(it) { data -> Snackbar(snackbarData = data) }
                    }) { innerPadding ->
                    Box(
                        modifier = Modifier.padding(
                            0.dp, 0.dp, 0.dp, innerPadding.calculateBottomPadding()
                        )
                    ) {
                        BodyLoggerNavigation(
                            navController = navController,
                            viewModel = viewModel
                        )
                    }
                }
            }
        }
    }
}
