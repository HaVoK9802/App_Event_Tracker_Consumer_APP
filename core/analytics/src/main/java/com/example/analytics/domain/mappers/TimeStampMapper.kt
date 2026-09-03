package com.example.analytics.domain.mappers

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun Long.toTimeStampPretty(): String{
    return SimpleDateFormat(
        "hh:mm:ss a",
        Locale.getDefault()
    ).format(Date(this))
}