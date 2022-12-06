package tech.ivanpopov.bodylogger.ui

import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.chargemap.compose.numberpicker.NumberPicker

@Composable
@Preview
fun WeightInputScreen() {
    var pickerValue by remember { mutableStateOf(70) }

    NumberPicker(
        value = pickerValue,
        range = 5..200,
        onValueChange = {
            pickerValue = it
        }
    )
}