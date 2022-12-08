package tech.ivanpopov.bodylogger

import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*


fun stringToEpochSeconds(stringDate: String): Long =
    SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault()).parse(stringDate)
        ?.toInstant()?.epochSecond ?: 0L

fun epochSecondsToDate(date: Long): String {
    val formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");
    val instant = Instant.ofEpochMilli(date)
    return formatter.format(
        LocalDateTime.ofInstant(
            instant,
            ZoneId.systemDefault()
        )
    )
}