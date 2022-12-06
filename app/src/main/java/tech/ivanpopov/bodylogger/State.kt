package tech.ivanpopov.bodylogger

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.toMutableStateList

class State(
    initialMessages: List<WeightEntry>
) {
    private val _weights: MutableList<WeightEntry> = initialMessages.toMutableStateList()
    val weights: List<WeightEntry> = _weights

    fun addWeight(weight: WeightEntry) {
        _weights.add(0, weight) // Add to the beginning of the list
    }
}

/*96.5; Dec 31, 2014 06:32; Google Fit*/

@Immutable
data class WeightEntry(
    val weightValue: String, //String value of float
    val date: String, //String format MMM dd, yyyy HH:mm
    val source: String? = null//ex: Google Fit
)
