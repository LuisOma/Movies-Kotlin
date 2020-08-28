package com.example.movies.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object utils {
    @RequiresApi(Build.VERSION_CODES.O)
    fun getStringFormatted(datestring: String): String? {
        var desiredFormat: String
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = formatter.parse(datestring)
        desiredFormat = DateTimeFormatter.ofPattern("dd-MMM-yyyy").format(date)
        return desiredFormat
    }
}