package tech.ivanpopov.bodylogger.data

import tech.ivanpopov.bodylogger.State
import tech.ivanpopov.bodylogger.WeightEntry

private val initialWeights = listOf(
    WeightEntry("96.5", "Dec 31, 2014 06:32", "Google Fit"),
    WeightEntry("86.1", "Feb 09, 2015 06:06", "Google Fit"),
    WeightEntry("105.0", "Feb 10, 2016 05:19"),
    WeightEntry("94.9", "Feb 10, 2017 05:20"),
    WeightEntry("114.9", "Feb 10, 2018 06:12", "Google Fit")
)

val bodyLoggerState = State(
    initialMessages = initialWeights
)
