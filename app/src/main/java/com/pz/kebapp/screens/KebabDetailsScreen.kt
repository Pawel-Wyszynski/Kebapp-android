package com.pz.kebapp.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Comment
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.GetApp
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KebabDining
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.LunchDining
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Store
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.Web
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.pz.kebapp.R
import com.pz.kebapp.components.ButtonComponent
import com.pz.kebapp.components.LongTextFieldComponent
import com.pz.kebapp.components.translatedDay
import com.pz.kebapp.components.translatedStatus
import com.pz.kebapp.components.translatedType
import com.pz.kebapp.data.models.Data
import com.pz.kebapp.data.models.MeatType
import com.pz.kebapp.data.models.Sauce
import com.pz.kebapp.functions.userFunctions.commentKebabFunction
import com.pz.kebapp.functions.userFunctions.deleteCommentFunction
import com.pz.kebapp.functions.userFunctions.likeKebabFunction
import com.pz.kebapp.navigation.BottomNavigationBar
import com.pz.kebapp.ui.theme.Background
import com.pz.kebapp.ui.theme.Gray
import com.pz.kebapp.ui.theme.nunitoSansFontFamily
import com.pz.kebapp.viewModel.DetailsViewModel
import com.pz.kebapp.viewModel.UserViewModel

@Composable
fun KebabDetailsScreen(
    id: Int,
    navController: NavHostController,
    viewModel: DetailsViewModel
) {
    val kebabsList = viewModel.state.kebabs
    val kebab = viewModel.selectedKebab
    val commentState = remember { mutableStateOf("") }
    val state = viewModel.state
    val context = LocalContext.current
    val userViewModel = remember { UserViewModel(context) }
    val userState = userViewModel.state

    Scaffold(
        bottomBar = {
            BottomNavigationBar(screen = "Kebaby", navController)
        },
        content = { paddingValues ->

            LaunchedEffect(key1 = id, key2 = kebabsList) {
                if (kebabsList.isNotEmpty()) {
                    viewModel.getKebab(id)
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Background),
                contentAlignment = Alignment.TopCenter
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .matchParentSize()
                            .padding(top = 28.dp, bottom = paddingValues.calculateBottomPadding())
                    ) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(top = 80.dp),
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

                                    MiddleBar(kebab = kebab, context, navController)

                                    KebabInfo(
                                        Icons.Default.Place,
                                        "Adres:",
                                        it.address
                                    )
                                    KebabInfo(
                                        Icons.Default.LocationOn,
                                        "Koordynaty:",
                                        "X: ${it.coordinatesX}, Y: ${it.coordinatesY}"
                                    )
                                    KebabInfo(
                                        Icons.Default.Web,
                                        "Strona internetowa:",
                                        if (it.websiteLink == null) {
                                            "Brak danych"
                                        } else {
                                            it.websiteLink.toString()
                                        }
                                    )
                                    KebabInfo(
                                        Icons.Default.GetApp,
                                        "Aplikacja mobilna:",
                                        if (it.appLink == null) {
                                            "Brak danych"
                                        } else {
                                            it.appLink.toString()
                                        }
                                    )
                                    KebabInfo(
                                        Icons.Default.Event,
                                        "Rok otwarcia:",
                                        if (it.openingYear == null) {
                                            "Brak danych"
                                        } else {
                                            it.openingYear.toString()
                                        }
                                    )
                                    KebabInfo(
                                        Icons.Default.Event,
                                        "Rok zamknięcia:",
                                        if (it.closingYear == null) {
                                            "Brak danych"
                                        } else {
                                            it.closingYear.toString()
                                        }
                                    )
                                    KebabInfo(
                                        Icons.Default.Star,
                                        "Ocena z Glovo:",
                                        if (it.glovoRates == null) {
                                            "Brak danych"
                                        } else {
                                            "${it.glovoRates}% użytkowników Glovo poleca tego kebaba"
                                        }
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
                                    KebabInfo(
                                        Icons.Default.Store,
                                        "Sieciówka:",
                                        it.network ?: "Brak danych"
                                    )
                                    KebabInfo(
                                        Icons.Default.Phone,
                                        "Telefon:",
                                        it.phoneNumber ?: "Brak danych"
                                    )

                                    Row {
                                        Icon(
                                            imageVector = Icons.Default.KebabDining,
                                            contentDescription = null,
                                            tint = Color.Black
                                        )
                                        Text(
                                            text = "Dostępne mięsa:",
                                            modifier = Modifier.padding(start = 10.dp),
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Bold,
                                            fontFamily = nunitoSansFontFamily
                                        )
                                    }
                                    CheckboxesList(
                                        availableTypes = it.meatTypes,
                                        allTypes = listOf(
                                            "Chicken",
                                            "Beef",
                                            "Lamb",
                                            "Pork",
                                            "Falafel"
                                        )
                                    )

                                    Row {
                                        Icon(
                                            imageVector = Icons.Default.LunchDining,
                                            contentDescription = null,
                                            tint = Color.Black
                                        )
                                        Text(
                                            text = "Dostępne sosy:",
                                            modifier = Modifier.padding(start = 10.dp),
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Bold,
                                            fontFamily = nunitoSansFontFamily
                                        )
                                    }
                                    CheckboxesList(
                                        availableTypes = it.sauces,
                                        allTypes = listOf("Mild", "Garlic", "Spicy", "Mixed")
                                    )

                                    it.openingHours.forEach { hour ->
                                        KebabInfo(
                                            Icons.Default.Schedule,
                                            "${translatedDay(hour.weekDay)}: ",
                                            "${hour.opensAt} - ${hour.closesAt}"
                                        )
                                    }

                                    LongTextFieldComponent(
                                        labelValue = "Komentarz",
                                        icon = Icons.Default.Description,
                                        textValue = commentState
                                    )

                                    ButtonComponent(value = "Potwierdź", onSelect = {
                                        commentKebabFunction(
                                            id,
                                            commentState.value,
                                            context,
                                            navController
                                        )
                                    })

                                    it.comments.forEach { comment ->
                                        Row(
                                            modifier = Modifier.padding(vertical = 8.dp),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.Start
                                        ) {
                                            Icon(
                                                imageVector = Icons.AutoMirrored.Filled.Comment,
                                                contentDescription = null,
                                                tint = Color.Black
                                            )
                                            Spacer(modifier = Modifier.width(10.dp))
                                            Text(
                                                text = comment.authorName,
                                                fontSize = 18.sp,
                                                fontWeight = FontWeight.Bold,
                                                fontFamily = nunitoSansFontFamily
                                            )
                                            Spacer(modifier = Modifier.weight(1f))
                                            if (comment.authorId == userState.id) {
                                                IconButton(onClick = {
                                                    deleteCommentFunction(
                                                        id,
                                                        comment.id,
                                                        context,
                                                        navController
                                                    )
                                                }) {
                                                    Icon(
                                                        imageVector = Icons.Default.Delete,
                                                        contentDescription = null,
                                                        tint = Color.Red
                                                    )
                                                }
                                            }
                                        }
                                        Text(text = comment.text, fontSize = 18.sp)
                                        Divider(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(vertical = 8.dp),
                                            color = Gray,
                                            thickness = 1.dp
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun CheckboxesList(
    availableTypes: List<Any>,
    allTypes: List<String>
) {
    allTypes.forEach { type ->
        val isChecked = when {
            availableTypes.isNotEmpty() && availableTypes[0] is MeatType ->
                (availableTypes as List<MeatType>).any { it.name == type }

            availableTypes.isNotEmpty() && availableTypes[0] is Sauce ->
                (availableTypes as List<Sauce>).any { it.name == type }

            else -> availableTypes.any { it == type }
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = {},
                colors = CheckboxDefaults.colors(checkedColor = Color.Green)
            )
            Text(
                text = translatedType(type),
                fontSize = 18.sp,
                modifier = Modifier.padding(1.dp)
            )
        }
    }
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        color = Gray,
        thickness = 1.dp
    )
}

@Composable
fun KebabInfo(icon: ImageVector, label: String, value: String) {
    val context = LocalContext.current

    Row {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.Black
        )
        Text(
            text = label,
            modifier = Modifier.padding(start = 10.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = nunitoSansFontFamily
        )
    }
    if (value.startsWith("http")) {
        val annotatedLinkString = buildAnnotatedString {
            withStyle(
                style = TextStyle(
                    color = Color.Blue,
                    textDecoration = TextDecoration.Underline
                ).toSpanStyle()
            ) {
                append(value)
            }
            addStringAnnotation(
                tag = "URL",
                annotation = value,
                start = 0,
                end = value.length
            )
        }
        ClickableText(
            text = annotatedLinkString,
            style = TextStyle(fontSize = 18.sp),
            onClick = { offset ->
                annotatedLinkString.getStringAnnotations("URL", offset, offset)
                    .firstOrNull()?.let { annotation ->
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(annotation.item))
                        context.startActivity(intent)
                    }
            }
        )
    } else {
        Text(text = value, fontSize = 18.sp)
    }
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        color = Gray,
        thickness = 1.dp
    )
}

@Composable
fun MiddleBar(kebab: Data?, context: Context, navController: NavHostController) {
    val userViewModel = remember { UserViewModel(context) }
    val state = userViewModel.state

    val isLiked = state.likedKebabs.any { likedKebab ->
        likedKebab.id == kebab?.id
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.Info,
            contentDescription = null,
            tint = Color.Black
        )
        Text(
            text = translatedStatus(kebab!!.status),
            modifier = Modifier.padding(start = 6.dp)
        )

        IconButton(onClick = {
            likeKebabFunction(id = kebab.id, context, navController)
        }) {
            Icon(
                imageVector = Icons.Default.ThumbUp,
                contentDescription = null,
                tint = if (isLiked) Color.Green else Color.Black
            )
        }
        Text(
            text = "${kebab.likeCount}"
        )

        Spacer(modifier = Modifier.width(10.dp))

        Icon(
            imageVector = Icons.Default.AccessTime,
            contentDescription = null,
            tint = Color.Black
        )
        if (kebab.isOpenNow) {
            Text(
                modifier = Modifier.padding(start = 6.dp),
                text = "Otwarte",
                color = Color.Green
            )
        } else {
            Text(
                modifier = Modifier.padding(start = 6.dp),
                text = "Zamknięte",
                color = Color.Red
            )
        }
    }
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        color = Gray,
        thickness = 1.dp
    )
}