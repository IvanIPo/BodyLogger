package tech.ivanpopov.bodylogger.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import tech.ivanpopov.bodylogger.WeightEntry
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun ChartScreen(data: List<WeightEntry>) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        AndroidView(
            modifier = Modifier
                .fillMaxSize(),
            factory = { context ->
                LineChart(context)
            },
            update = {
                it.updateChartData(weightEntries = data)
            }
        )
    }
}

private fun LineChart.updateChartData(weightEntries: List<WeightEntry>) {
    val entries: MutableList<Entry> = ArrayList()
    for (weightEntry in weightEntries) {
        entries.add(
            Entry(
                stringToEpochSeconds(weightEntry.date).toFloat(),
                weightEntry.weightValue.toFloat()
            )
        )
    }
    val dataSet = LineDataSet(entries, "Label") // add entries to dataset
    dataSet.setDrawCircles(true)
    dataSet.setDrawFilled(true)
    xAxis.position = XAxis.XAxisPosition.BOTTOM
    xAxis.setDrawAxisLine(true)
    xAxis.setDrawLabels(true)
    xAxis.setDrawLabels(true)
    /*chart.axisLeft.axisMinimum = 0f
    chart.axisLeft.axisMaximum = 250f*/
    axisRight.isEnabled = false
    xAxis.valueFormatter = LineChartXAxisValueFormatter()
    description.isEnabled = false
    setDrawBorders(true)
    legend.isEnabled = false
    val lineData = LineData(dataSet)
    this.data = lineData
    invalidate() // refresh
}

private fun stringToEpochSeconds(stringDate: String): Long =
    SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault()).parse(stringDate)
        ?.toInstant()?.epochSecond ?: 0L

class LineChartXAxisValueFormatter : IndexAxisValueFormatter() {
    override fun getFormattedValue(value: Float): String {

        // Convert float value to date string
        // Convert from seconds back to milliseconds to format time  to show to the user
        val emissionsMilliSince1970Time = value.toLong() * 1000

        // Show time in local version
        val timeMilliseconds = Date(emissionsMilliSince1970Time)
        val dateTimeFormat: DateFormat =
            DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return dateTimeFormat.format(timeMilliseconds)
    }
}