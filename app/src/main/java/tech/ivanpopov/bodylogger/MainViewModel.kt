package tech.ivanpopov.bodylogger

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import tech.ivanpopov.bodylogger.navigation.Screen

class MainViewModel : ViewModel() {

    private val _currentScreen = MutableStateFlow<Screen>(Screen.Main)
    val currentScreen: StateFlow<Screen> = _currentScreen

    fun setNavigateTo(screen: Screen) {
        _currentScreen.value = screen
    }
}
