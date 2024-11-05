package com.pz.kebapp.components

import androidx.compose.runtime.Composable

@Composable
fun translatedDay(status: String): String {
    return when (status) {
        "Monday" -> "Poniedziałek"
        "Tuesday" -> "Wtorek"
        "Wednesday" -> "Środa"
        "Thursday" -> "Czwartek"
        "Friday" -> "Piątek"
        "Saturday" -> "Sobota"
        "Sunday" -> "Niedziela"
        else -> "Błędny dzień tygodnia"
    }
}


@Composable
fun translatedStatus(status: String): String {
    return when (status) {
        "active" -> "Aktywny"
        "inactive" -> "Nieaktywny"
        "planned" -> "Planowany"
        else -> "Nieznany"
    }
}