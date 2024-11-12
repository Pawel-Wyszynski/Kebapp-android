package com.pz.kebapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Store
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.pz.kebapp.R
import com.pz.kebapp.data.models.Data
import com.pz.kebapp.data.models.MeatType
import com.pz.kebapp.data.models.Sauce
import com.pz.kebapp.ui.theme.Background
import com.pz.kebapp.ui.theme.nunitoSansFontFamily
import com.pz.kebapp.viewModel.DetailsViewModel

@Composable
fun KebabDetailsComponent(id: Int, viewModel: DetailsViewModel, paddingValues: PaddingValues) {
    val kebabsList = viewModel.state.kebabs
    val kebab = viewModel.selectedKebab
    val messageState = remember { mutableStateOf("") }
    val state = viewModel.state

    LaunchedEffect(key1 = id, key2 = kebabsList) {
        if (kebabsList.isNotEmpty()) {
            viewModel.getKebab(id)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        BackgroundGradient()

        LazyColumn(
            modifier = Modifier
                .matchParentSize()
                .padding(top = 10.dp, bottom = paddingValues.calculateBottomPadding())
        ) {
            item {
                if (state.isLoading) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
            item {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.TopCenter
                ) {
                    ForegroundCover(kebab = kebab)
                }
            }

            item {
                Column(
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp, bottom = 50.dp)
                        .align(Alignment.BottomCenter),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    kebab?.let {
                        Text(
                            text = it.name,
                            fontSize = 38.sp,
                            fontFamily = nunitoSansFontFamily,
                            lineHeight = 40.sp,
                            textAlign = TextAlign.Center
                        )

                        MiddleBar(kebab)

                        KebabInfo(Icons.Default.Place, "Adres:", it.address ?: "Brak danych")
                        KebabInfo(
                            Icons.Default.LocationOn,
                            "Koordynaty:",
                            "X: ${it.coordinatesX}, Y: ${it.coordinatesY}" ?: "Brak danych"
                        )
                        KebabInfo(
                            Icons.Default.Event,
                            "Rok otwarcia:",
                            it.openingYear.toString() ?: "Brak danych"
                        )
                        KebabInfo(
                            Icons.Default.Event,
                            "Rok zamknięcia:",
                            it.closingYear.toString() ?: "Brak danych"
                        )
                        KebabInfo(
                            Icons.Default.Info,
                            "Status:",
                            translatedStatus(it.status) ?: "Brak danych"
                        )
                        KebabInfo(
                            Icons.Default.Build,
                            "Czy kebab jest kraftowy:",
                            if (it.isKraft) "Tak" else "Nie"
                        )
                        KebabInfo(
                            Icons.Default.DirectionsCar,
                            "Czy kebab jest w przyczepie:",
                            if (it.isFoodTruck) "Tak" else "Nie"
                        )
                        KebabInfo(Icons.Default.Store, "Sieciówka:", it.network ?: "Brak danych")
                        KebabInfo(Icons.Default.Phone, "Telefon:", it.phoneNumber ?: "Brak danych")

                        Text(
                            text = "Dostępne mięsa",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        MeatTypeCheckboxes(it.meatTypes)

                        Text(
                            text = "Dostępne sosy",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        SauceTypeCheckboxes(it.sauces)

                        it.openingHours.forEach { hour ->
                            KebabInfo(
                                Icons.Default.Schedule,
                                "${translatedDay(hour.weekDay)}: ",
                                "od ${hour.opensAt} do ${hour.closesAt}"
                            )
                        }

                        LongTextFieldComponent(
                            labelValue = "Komentarz",
                            icon = Icons.Default.Description,
                            textValue = messageState
                        )

                        ButtonComponent(value = "Wyślij", onSelect = {})


                    }
                }
            }
        }
    }
}

@Composable
fun MeatTypeCheckboxes(availableMeats: List<MeatType>) {
    val allMeats = listOf("Chicken", "Beef", "Lamb", "Pork", "Falafel")
    allMeats.forEach { meat ->
        val isChecked = availableMeats.any { it.name == meat }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = {},
                colors = CheckboxDefaults.colors(checkedColor = Color.Green)
            )
            Text(
                text = translatedMeatType(meat),
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(1.dp)
            )
        }
    }
}

@Composable
fun SauceTypeCheckboxes(availableSauces: List<Sauce>) {
    val allMeats = listOf("Mild", "Garlic", "Spicy", "Mixed")
    allMeats.forEach { sauce ->
        val isChecked = availableSauces.any { it.name == sauce }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = {},
                colors = CheckboxDefaults.colors(checkedColor = Color.Green)
            )
            Text(
                text = translatedSauceType(sauce),
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(1.dp)
            )
        }
    }
}

@Composable
fun BackgroundGradient() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    )
}

@Composable
fun ForegroundCover(kebab: Data?) {
    Box(
        modifier = Modifier
            .wrapContentHeight()
            .width(250.dp)
            .padding(top = 80.dp)
            .clip(RoundedCornerShape(16.dp)),
        contentAlignment = Alignment.TopCenter
    ) {
        AsyncImage(
            model = kebab?.logoUrl ?: R.drawable.brodacz,
            contentDescription = kebab?.name,
            modifier = Modifier
                .width(250.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun KebabInfo(icon: ImageVector, label: String, value: String) {
    Column(
        modifier = Modifier
            .padding(vertical = 4.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier
                    .size(20.dp)
                    .padding(end = 8.dp)
            )
            Text(
                text = label,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Text(
            text = value,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun MiddleBar(kebab: Data?) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = null,
            tint = Color.Black
        )
        Text(
            text = "Brak oceny",
            modifier = Modifier.padding(start = 6.dp)
        )
        Spacer(modifier = Modifier.width(20.dp))

        Icon(
            imageVector = Icons.Default.ThumbUp,
            contentDescription = null,
            tint = Color.Black
        )
        Text(
            text = kebab?.likeCount.toString(),
            modifier = Modifier
                .padding(start = 6.dp)
        )

        Spacer(modifier = Modifier.width(20.dp))
        Icon(
            imageVector = Icons.Default.AccessTime,
            contentDescription = null,
            tint = Color.Black
        )
        if (kebab?.isOpenNow == true) {
            Text(
                modifier = Modifier.padding(start = 6.dp),
                text = "Otwarte",
                color = Color.Green
            )
        } else {
            Text(
                text = "Zamknięte",
                color = Color.Red
            )
        }
    }
}
