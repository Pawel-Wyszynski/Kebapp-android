package com.pz.kebapp.components

import androidx.compose.runtime.Composable

@Composable
fun translatedDay(day: String): String {
    return when (day) {
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

@Composable
fun translatedMeatType(meat: String): String {
    return when (meat) {
        "Chicken" -> "Kurczak"
        "Beef" -> "Wołowina"
        "Lamb" -> "Jagnięcina"
        "Pork" -> "Wieprzowina"
        "Falafel" -> "Falafel"
        else -> "Nieznany"
    }
}

@Composable
fun translatedSauceType(sauce: String): String {
    return when (sauce) {
        "Mild" -> "Łagodny"
        "Garlic" -> "Czosnkowy"
        "Spicy" -> "Pikantny"
        "Mixed" -> "Mieszany"
        else -> "Nieznany"
    }
}