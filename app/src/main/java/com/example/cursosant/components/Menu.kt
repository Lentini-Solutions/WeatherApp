package com.example.cursosant.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.example.cursosant.R
import com.example.cursosant.common.entities.Location
import com.example.cursosant.common.fakedata.getAllCitiesPreview

@Composable
fun <T>AntDropDownMenu(
    items: List<T> = emptyList(),
    labelRes: Int,
    onSelect: (T) -> Unit
){

    var selectedItem by remember { mutableStateOf<T?>(null) }
    var isExpanded by remember { mutableStateOf(false) }

    Box() {
        OutlinedButton(
            onClick = {
                isExpanded = !isExpanded
            }
        ) {
            Text(
                text = selectedItem?.toString() ?: stringResource(labelRes),
            )
            Icon(Icons.Default.ArrowDropDown, contentDescription = null)
        }
        DropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }){
            items.forEach { city ->
                DropdownMenuItem(
                    onClick = {
                        isExpanded = false
                        onSelect(city)
                        selectedItem = city
                    },
                    text = {
                        Text(text = city.toString())
                    }
                )
            }
        }
    }
}