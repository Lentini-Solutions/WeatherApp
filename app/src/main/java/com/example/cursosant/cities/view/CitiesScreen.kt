package com.example.cursosant.cities.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cursosant.R
import com.example.cursosant.cities.viewmodel.CitiesViewModel
import com.example.cursosant.common.entities.Location
import com.example.cursosant.components.AntDialogInfo
import com.example.cursosant.components.AntProgressFullScreen
import com.example.cursosant.components.AntSnackbar
import com.example.cursosant.components.AntTextTitle
import com.example.cursosant.ui.theme.Common_padding_default
import com.example.cursosant.ui.theme.Common_padding_x_large
import com.example.cursosant.ui.theme.CursosANTTheme
import com.example.cursosant.ui.theme.Typography
import com.example.cursosant.ui.theme.msgVerticalSpace
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Preview(showSystemUi = true)
@Composable
fun CitiesPreview(modifier: Modifier = Modifier) {
    CursosANTTheme() {
        CitiesView(modifier = modifier.padding(top = 24.dp))
    }
}

@Composable
fun CitiesView(
    modifier: Modifier = Modifier,
    vm: CitiesViewModel = koinViewModel()
) {

    val uiState by vm.getUiState().collectAsState()
    var openDialog by remember { mutableStateOf(false) }
    var selectedCity by remember { mutableStateOf<Location?>(null) }

    Box(modifier = modifier.fillMaxSize()) {
        Column() {
            AntTextTitle(R.string.cities_title)
            if(uiState.items.isNotEmpty()){
                LazyColumn() {
                    items(uiState.items.size){ index ->
                        val city = uiState.items[index]
                        ItemCityView(city, onOpenMap = { city ->
                            vm.showMap(city)
                        },
                            onRemove = { city ->
                                selectedCity = city
                                openDialog = true
                            })
                    }
                }
            }else{
                Text(
                    text = stringResource(R.string.empty_cities_room_register),
                    style = Typography.headlineSmall,
                    modifier = Modifier
                        .padding(Common_padding_x_large)
                        .fillMaxSize(),
                    textAlign = TextAlign.Center
                )
            }
        }

        if(openDialog){
            selectedCity?.let {
                AntDialogInfo(
                    info = "¿Está seguro de borrar este dato almacenado? No hay retorno una vez borrado",
                    titleRes = R.string.delete_title,
                    onDismissRequest = { isSuccess ->
                        if(isSuccess) vm.deleteCity(it)
                        openDialog = false
                    }
                )
            }
        }

        if(uiState.msgRes.toString().isNotEmpty() && uiState.items.isNotEmpty()){
            AntSnackbar(
                modifier = Modifier
                    .fillMaxWidth(),
                msgRes = uiState.msgRes,
                colorBackground = Color.Cyan,
                onDismiss = {
                    vm.clearMsg()
                }
            )
        }

        AnimatedVisibility(uiState.inProgress, enter = fadeIn(), exit = fadeOut()) {
            AntProgressFullScreen()
        }
    }

}