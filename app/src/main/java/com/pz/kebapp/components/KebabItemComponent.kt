package com.pz.kebapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pz.kebapp.data.models.KebabsListItem
import com.pz.kebapp.ui.theme.Gray
import com.pz.kebapp.ui.theme.nunitoSansFontFamily

@Composable
fun KebabItemComponent(
    kebab: KebabsListItem,
    icon: ImageVector,
    onSelect: () -> Unit
) {
    val translatedStatus = translatedStatus(kebab.status)

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable(onClick = onSelect),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = kebab.name,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = nunitoSansFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Normal
                    )
                )
                Text(
                    text = kebab.address,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = nunitoSansFontFamily,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal,
                        color = Color.Gray
                    )
                )
                Text(
                    text = translatedStatus,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = nunitoSansFontFamily,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Italic,
                        color = when (kebab.status) {
                            "active" -> Color.Green
                            "inactive" -> Color.Red
                            "planned" -> Color.Blue
                            else -> Color.Gray
                        }
                    )
                )
            }
            Icon(
                imageVector = icon,
                contentDescription = null
            )
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            color = Gray,
            thickness = 1.dp
        )
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