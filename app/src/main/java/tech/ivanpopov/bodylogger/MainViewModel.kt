package tech.ivanpopov.bodylogger

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import tech.ivanpopov.bodylogger.data.AppDatabase
import tech.ivanpopov.bodylogger.data.BodyMeasurement
import tech.ivanpopov.bodylogger.data.Repository
import tech.ivanpopov.bodylogger.navigation.Screen

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val measurements: LiveData<List<BodyMeasurement>>
    private val repository: Repository
    val searchResults: MutableLiveData<List<BodyMeasurement>>

    init {
        val productDb = AppDatabase.getInstance(application)
        val productDao = productDb.bodyMeasurementDao()
        repository = Repository(productDao)

        measurements = repository.measurements
        searchResults = repository.searchResults
    }

    fun insertMeasurements(measurements: List<BodyMeasurement>) {
        repository.insertMeasurements(measurements)
    }

    fun insertMeasurement(measurement: BodyMeasurement) {
        repository.insertMeasurement(measurement)
    }

    fun findProduct(dateTime: Long) {
        repository.findMeasurement(dateTime)
    }

    fun deleteProduct(dateTime: Long) {
        repository.deleteMeasurement(dateTime)
    }


    private val _currentScreen = MutableStateFlow<Screen>(Screen.Main)
    val currentScreen: StateFlow<Screen> = _currentScreen

    fun setNavigateTo(screen: Screen) {
        _currentScreen.value = screen
    }
}
