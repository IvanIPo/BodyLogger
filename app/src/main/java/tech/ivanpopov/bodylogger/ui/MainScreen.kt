package tech.ivanpopov.bodylogger.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.ivanpopov.bodylogger.MainViewModel
import tech.ivanpopov.bodylogger.R
import tech.ivanpopov.bodylogger.data.BodyMeasurement
import java.util.*

@Composable
fun MainScreen(viewModel: MainViewModel) {

    val measurements by viewModel.measurements.observeAsState(arrayListOf())

    val latestWeightValue: String? =
        if (measurements.isNotEmpty()) measurements[0].weight.toString() else null

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        val showDialog = remember { mutableStateOf(false) }

        if (showDialog.value)
            WeightInputDialog(
                value = "",
                setShowDialog = {
                    showDialog.value = it
                },
                setValue = { newValue ->
                    viewModel.insertMeasurement(
                        BodyMeasurement(
                            Date().time,
                            newValue.toFloat()
                        )
                    )
                }
            )
        Box(modifier = Modifier.background(color = MaterialTheme.colors.background)) {
            Image(
                painter = painterResource(R.drawable.ic_baseline_monitor_weight_24),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
            )
            Text(
                text = latestWeightValue ?: "--",
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(vertical = 245.dp),
                fontSize = if (latestWeightValue == null) 64.sp else 40.sp
            )
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(vertical = 220.dp)
                    .height(60.dp),
                onClick = { showDialog.value = true },
            ) {
                Text(text = stringResource(R.string.enter_weight), color = Color.Black)
            }
        }
    }
}