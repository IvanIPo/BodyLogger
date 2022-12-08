package tech.ivanpopov.bodylogger.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import tech.ivanpopov.bodylogger.MainViewModel
import tech.ivanpopov.bodylogger.data.BodyMeasurement
import java.text.DateFormat
import java.util.*


@Composable
fun ChartScreen(viewModel: MainViewModel) {
    val measurements by viewModel.measurements.observeAsState(arrayListOf())

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Log.d("BodyLoggerLog", "Measurements to build: $measurements")
        AndroidView(
            modifier = Modifier
                .fillMaxSize(),
            factory = { context ->
                LineChart(context)
            },
            update = {
                it.updateChartData(bodyMeasurements = measurements)
            }
        )
    }
}

private fun LineChart.updateChartData(bodyMeasurements: List<BodyMeasurement>) {
    val entries: MutableList<Entry> = ArrayList()
    for (bodyMeasurement in bodyMeasurements) {
        entries.add(
            Entry(
                (bodyMeasurement.dateTime/1000).toFloat(),
                bodyMeasurement.weight ?: 0f
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
    axisRight.isEnabled = false
    xAxis.valueFormatter = LineChartXAxisValueFormatter()
    description.isEnabled = false
    setDrawBorders(true)
    legend.isEnabled = false
    val lineData = LineData(dataSet)
    this.data = lineData
    invalidate()
}

class LineChartXAxisValueFormatter : IndexAxisValueFormatter() {
    override fun getFormattedValue(value: Float): String {
        val emissionsMilliSince1970Time = value.toLong() * 1000
        val timeMilliseconds = Date(emissionsMilliSince1970Time)
        val dateTimeFormat: DateFormat =
            DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return dateTimeFormat.format(timeMilliseconds)
    }
}