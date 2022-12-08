package tech.ivanpopov.bodylogger.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

class Repository(private val bodyMeasurementDao: BodyMeasurementDao) {

    val measurements: LiveData<List<BodyMeasurement>> = bodyMeasurementDao.getBodyMeasurementsList()
    val searchResults = MutableLiveData<List<BodyMeasurement>>()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertMeasurements(measurements: List<BodyMeasurement>) {
        coroutineScope.launch(Dispatchers.IO) {
            bodyMeasurementDao.insertBodyMeasurementsList(measurements)
        }
    }

    fun insertMeasurement(measurement: BodyMeasurement) {
        coroutineScope.launch(Dispatchers.IO) {
            bodyMeasurementDao.insertBodyMeasurement(measurement)
        }
    }

    fun deleteMeasurement(dateTime: Long) {
        coroutineScope.launch(Dispatchers.IO) {
            bodyMeasurementDao.deleteBodyMeasurementEntry(dateTime)
        }
    }

    fun findMeasurement(dateTime: Long) {
        coroutineScope.launch(Dispatchers.Main) {
            searchResults.value = findAsync(dateTime).await()
        }
    }

    private fun findAsync(dateTime: Long): Deferred<List<BodyMeasurement>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async bodyMeasurementDao.findMeasurement(dateTime)
        }
}