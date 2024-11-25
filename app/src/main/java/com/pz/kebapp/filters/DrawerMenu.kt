package com.pz.kebapp.filters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pz.kebapp.ui.theme.nunitoSansFontFamily
import com.pz.kebapp.viewModel.KebabViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

data class FilterItem(
    val key: String,
    val label: String,
    var isSelected: Boolean = false
)

data class SortOption(
    val key: String,
    val label: String
)

@Composable
fun DrawerMenu(
    filters: List<FilterItem>,
    meatFilterState: MutableList<Boolean>,
    sauceFilterState: MutableList<Boolean>,
    statusFilterState: MutableList<Boolean>,
    selectedSort: MutableState<String?>,
    isAscending: MutableState<Boolean>,
    coroutineScope: CoroutineScope,
    drawerState: DrawerState,
    kebabViewModel: KebabViewModel
) {
    ModalDrawerSheet {
        LazyColumn(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text(
                    "Metody sortowania",
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = nunitoSansFontFamily
                )
            }
            item {
                SortRadioButtonGroup(selectedSort)
            }
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Sortowanie: ",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(end = 8.dp)
                    )

                    IconButton(
                        onClick = { isAscending.value = !isAscending.value },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = if (isAscending.value) Icons.Default.ArrowUpward else Icons.Default.ArrowDownward,
                            contentDescription = if (isAscending.value) "Rosnąco" else "Malejąco",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }

                    Text(
                        text = if (isAscending.value) "Rosnąco" else "Malejąco",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
            item {
                Spacer(Modifier.height(16.dp))
                Text(
                    "Filtry",
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = nunitoSansFontFamily
                )
            }

            items(filters) { filter ->
                FilterCheckbox(filter)
            }
            item {
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    color = Color.Gray,
                    thickness = 1.dp
                )
                Text(
                    text = "Status",
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = nunitoSansFontFamily
                )
                FilterCheckboxesList(
                    filterTypes = listOf("Aktywny", "Nieaktywny", "Planowany"),
                    filterStates = statusFilterState
                )
            }
            item {
                Text(
                    text = "Dostępne mięsa",
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = nunitoSansFontFamily
                )
                FilterCheckboxesList(
                    filterTypes = listOf(
                        "Kurczak",
                        "Wołowina",
                        "Jagnięcina",
                        "Wieprzowina",
                        "Falafel"
                    ),
                    filterStates = meatFilterState
                )
            }
            item {
                Text(
                    text = "Dostępne sosy",
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = nunitoSansFontFamily
                )
                FilterCheckboxesList(
                    filterTypes = listOf("Łagodny", "Czosnkowy", "Pikantny", "Mieszany"),
                    filterStates = sauceFilterState
                )
            }
            item {
                Button(
                    onClick = {
                        coroutineScope.launch {
                            kebabViewModel.applySort(
                                selectedSort.value,
                                isAscending.value
                            )
                            drawerState.close()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Text("Zastosuj")
                }
                Spacer(Modifier.height(16.dp))
            }
        }
    }
}


@Composable
fun FilterCheckbox(filter: FilterItem) {
    var isSelected by remember { mutableStateOf(filter.isSelected) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Checkbox(
            checked = isSelected,
            onCheckedChange = {
                isSelected = it
                filter.isSelected = it
            },
            colors = CheckboxDefaults.colors(checkedColor = Color.Green)
        )
        Text(text = filter.label, modifier = Modifier.padding(start = 8.dp))
    }
}

@Composable
fun FilterCheckboxesList(
    filterTypes: List<String>,
    filterStates: MutableList<Boolean>
) {
    filterTypes.forEachIndexed { index, filter ->
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Checkbox(
                checked = filterStates[index],
                onCheckedChange = { checked ->
                    filterStates[index] = checked
                },
                colors = CheckboxDefaults.colors(checkedColor = Color.Green)
            )
            Text(
                text = filter,
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        color = Color.Gray,
        thickness = 1.dp
    )
}


@Composable
fun SortRadioButtonGroup(selectedSort: MutableState<String?>) {
    val sortOptions = listOf(
        SortOption("name", "Nazwa"),
        SortOption("logo", "Logo"),
        SortOption("address", "Adres"),
        SortOption("coordinates", "Koordynaty"),
        SortOption("ratings", "Oceny"),
        SortOption("openingYear", "Rok otwarcia"),
        SortOption("closingYear", "Rok zamknięcia")
    )

    Column(Modifier.padding(horizontal = 16.dp)) {
        sortOptions.forEach { option ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                RadioButton(
                    selected = selectedSort.value == option.key,
                    onClick = { selectedSort.value = option.key }
                )
                Text(text = option.label, modifier = Modifier.padding(start = 8.dp))
            }
        }
    }
}
