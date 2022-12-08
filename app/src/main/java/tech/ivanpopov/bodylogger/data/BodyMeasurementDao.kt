package tech.ivanpopov.bodylogger.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BodyMeasurementDao {
    @Query("SELECT * FROM body_measurements_list ORDER BY dateTime DESC")
    fun getBodyMeasurementsList(): LiveData<List<BodyMeasurement>>

    @Query("SELECT * FROM body_measurements_list WHERE dateTime = :dateTime")
    fun findMeasurement(dateTime: Long): List<BodyMeasurement>

    @Query("DELETE FROM body_measurements_list WHERE dateTime = :dateTime")
    fun deleteBodyMeasurementEntry(dateTime: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBodyMeasurementsList(data: List<BodyMeasurement>)

    @Insert
    fun insertBodyMeasurement(measurement: BodyMeasurement)
}