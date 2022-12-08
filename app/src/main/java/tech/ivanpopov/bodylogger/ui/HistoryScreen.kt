@file:OptIn(ExperimentalFoundationApi::class)

package tech.ivanpopov.bodylogger.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import tech.ivanpopov.bodylogger.MainViewModel
import tech.ivanpopov.bodylogger.data.BodyMeasurement
import tech.ivanpopov.bodylogger.epochSecondsToDate

@Composable
fun HistoryScreen(viewModel: MainViewModel) {
    val measurements by viewModel.measurements.observeAsState(arrayListOf())
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        items(measurements.size) {
            WeightItemView(context = context, measurement = measurements[it]) {}
        }
    }
}

@Composable
private fun WeightItemView(
    context: Context,
    measurement: BodyMeasurement,
    onClick: (msg: String) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .combinedClickable(
                onClick = { },
                onLongClick = {
                    Toast
                        .makeText(context, "Do you wanna change this entry?", Toast.LENGTH_SHORT)
                        .show()
                },
            )
    ) {

        ConstraintLayout(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            val (weight, date) = createRefs()

            Text(
                modifier = Modifier.constrainAs(weight) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start, margin = 16.dp)
                },
                text = "${measurement.weight} kg"
            )
            Text(
                modifier = Modifier.constrainAs(date) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end, margin = 16.dp)
                },
                text = epochSecondsToDate(measurement.dateTime)
            )
        }
    }

}