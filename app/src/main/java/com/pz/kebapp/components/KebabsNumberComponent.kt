package com.pz.kebapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pz.kebapp.ui.theme.Background
import com.pz.kebapp.ui.theme.nunitoSansFontFamily

@Composable
fun KebabsNumberComponent(
    activeValue: Int,
    plannedValue: Int,
    inactiveValue: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Background)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Column(
            modifier = Modifier
                .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(4.dp))
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Aktywne",
                modifier = Modifier
                    .heightIn(),
                fontSize = 16.sp,
                fontFamily = nunitoSansFontFamily,
                fontWeight = FontWeight.Bold,
                color = Color.Green
            )
            Text(
                text = activeValue.toString(),
                fontSize = 16.sp,
                fontFamily = nunitoSansFontFamily,
                fontWeight = FontWeight.Bold,
                color = Color.Green
            )
        }
        Column(
            modifier = Modifier
                .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(4.dp))
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "W planach",
                modifier = Modifier
                    .heightIn(),
                fontSize = 16.sp,
                fontFamily = nunitoSansFontFamily,
                fontWeight = FontWeight.Bold,
                color = Color.Blue
            )
            Text(
                text = plannedValue.toString(),
                fontSize = 16.sp,
                fontFamily = nunitoSansFontFamily,
                fontWeight = FontWeight.Bold,
                color = Color.Blue
            )
        }
        Column(
            modifier = Modifier
                .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(4.dp))
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Zamknięte",
                modifier = Modifier
                    .heightIn(),
                fontSize = 16.sp,
                fontFamily = nunitoSansFontFamily,
                fontWeight = FontWeight.Bold,
                color = Color.Red
            )
            Text(
                text = inactiveValue.toString(),
                fontSize = 16.sp,
                fontFamily = nunitoSansFontFamily,
                fontWeight = FontWeight.Bold,
                color = Color.Red
            )
        }
    }
}