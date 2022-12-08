package tech.ivanpopov.bodylogger.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "body_measurements_list")
data class BodyMeasurement(

    @PrimaryKey
    val dateTime: Long,

    val weight: Float?,

    val source: String? = null
)