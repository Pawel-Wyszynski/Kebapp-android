package com.pz.kebapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.pz.kebapp.data.models.KebabsListItem
import com.pz.kebapp.ui.theme.KebabActive
import com.pz.kebapp.ui.theme.KebabAddress
import com.pz.kebapp.ui.theme.KebabInactive
import com.pz.kebapp.ui.theme.KebabName
import com.pz.kebapp.ui.theme.KebabPlanned
import com.pz.kebapp.ui.theme.KebabStatus


@Composable
fun KebabItemComponent(kebab: KebabsListItem, navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(3f)) {
            Text(
                text = kebab.name,
                fontWeight = FontWeight.Bold,
                fontSize = TextUnit(
                    18f,
                    TextUnitType.Sp
                ),
                color = KebabName
            )

            Text(
                text = kebab.address,
                fontSize = TextUnit(
                    14f,
                    TextUnitType.Sp
                ),
                color = KebabAddress
            )

            val translatedStatus = translateStatus(kebab.status)
            Text(
                text = translatedStatus,
                fontSize = TextUnit(
                    14f,
                    TextUnitType.Sp
                ),
                fontStyle = FontStyle.Italic,
                color = when (kebab.status) {
                    "active" -> KebabActive
                    "inactive" -> KebabInactive
                    "planned" -> KebabPlanned
                    else -> KebabStatus
                }
            )
        }
        Column(modifier = Modifier.weight(1f)) {
            DetailsButtonComponent(value = "Szczegóły", onSelect = {
                navController.navigate("details/${kebab.id}")
            })
        }
    }
}

@Composable
fun translateStatus(status: String): String {
    return when (status) {
        "active" -> "Aktywny"
        "inactive" -> "Nieaktywny"
        "planned" -> "Planowany"
        else -> "Nieznany"
    }
}
