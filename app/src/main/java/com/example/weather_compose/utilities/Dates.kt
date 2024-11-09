package com.example.weather_compose.utilities

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.time.temporal.ChronoUnit
import java.util.Locale

//Utility function to convert date to a more readable format
fun convertDate(dateString: String): String {
    //Define the date formatter
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault())

    // Parse the input date string to a LocalDate
    val date = LocalDate.parse(dateString, formatter)

    //Get the current date
    val today = LocalDate.now()

    return when {
        date.isEqual(today) -> "Today"
        date.isEqual(today.plus(1, ChronoUnit.DAYS)) -> "Tomorrow"
        else -> date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())



    }
}