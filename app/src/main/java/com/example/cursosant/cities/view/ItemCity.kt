package com.example.cursosant.cities.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowOutward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cursosant.common.entities.Location
import com.example.cursosant.common.fakedata.getCityPreview
import com.example.cursosant.ui.theme.Common_padding_List_Item_Vertical
import com.example.cursosant.ui.theme.Common_padding_default
import com.example.cursosant.ui.theme.Common_padding_min
import com.example.cursosant.ui.theme.CursosANTTheme
import com.example.cursosant.ui.theme.Typography

@Preview(showSystemUi = true)
@Composable
fun PreviewItemCity(){
    CursosANTTheme {
        ItemCityView(getCityPreview(), onOpenMap = {}, onRemove = {})
    }
}

@Composable
fun ItemCityView(
    city: Location,
    onOpenMap: (city: Location) -> Unit,
    onRemove: (city: Location) -> Unit
){

    val swipeToDismissBoxState = rememberSwipeToDismissBoxState(
        confirmValueChange = {
            if(it == SwipeToDismissBoxValue.EndToStart){
                onRemove(city)
            }
            it != SwipeToDismissBoxValue.EndToStart
        }
    )

    SwipeToDismissBox(
        state = swipeToDismissBoxState,
        modifier = Modifier.fillMaxSize(),
        enableDismissFromStartToEnd = false,
        backgroundContent = {
            when(swipeToDismissBoxState.dismissDirection){
                SwipeToDismissBoxValue.EndToStart -> {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Red)
                            .wrapContentSize(Alignment.CenterEnd)
                            .padding(Common_padding_List_Item_Vertical),
                        tint = Color.White
                    )
                }
                else -> {}
            }
        }) {
        Card(Modifier.padding(Common_padding_default)) {
            Row(modifier = Modifier.padding(top = 24.dp)) {
                Text(
                    text = "${city.name} (${city.country})",
                    modifier = Modifier
                        .weight(1f)
                        .padding(Common_padding_min),
                    style = Typography.headlineSmall
                )
                IconButton(onClick = { onOpenMap(city) }) {
                    Icon(Icons.Default.ArrowOutward, contentDescription = null)
                }
            }
        }
    }
}