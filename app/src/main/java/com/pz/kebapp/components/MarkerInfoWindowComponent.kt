package com.pz.kebapp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.pz.kebapp.data.models.Data
import com.pz.kebapp.ui.theme.nunitoSansFontFamily

@Composable
fun MarkerInfoWindowComponent(
    data: Data,
    navController: NavHostController
) {

    val translatedStatus = translatedStatus(data.status)
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = data.name,
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = nunitoSansFontFamily,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = data.address,
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = nunitoSansFontFamily,
                fontWeight = FontWeight.Normal,
                color = Color.Gray
            ),
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = translatedStatus,
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = nunitoSansFontFamily,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Italic,
                color = when (data.status) {
                    "active" -> Color.Green
                    "inactive" -> Color.Red
                    "planned" -> Color.Blue
                    else -> Color.Gray
                }
            ),
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = "Godziny otwarcia:",
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = nunitoSansFontFamily,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 4.dp)
        )
        data.openingHours.forEach { hour ->
            Text(
                text = "${translatedDay(hour.weekDay)}: ${hour.opensAt} - ${hour.closesAt}",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = nunitoSansFontFamily,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray
                )
            )
        }
        ClickableTextComponent(
            valueInitial = "",
            valueAnnotated = "Więcej informacji",
            onTextSelected = {
                navController.navigate("details/${data.id}")
            })
    }
}