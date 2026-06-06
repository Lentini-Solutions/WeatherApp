package com.example.cursosant.weather.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.cursosant.R
import com.example.cursosant.common.entities.Location
import com.example.cursosant.common.entities.WeatherCity
import com.example.cursosant.common.fakedata.getAllCitiesPreview
import com.example.cursosant.components.AntCoilImage
import com.example.cursosant.components.AntDropDownMenu
import com.example.cursosant.components.AntProgressFullScreen
import com.example.cursosant.components.AntSnackbar
import com.example.cursosant.components.AntTextTitle
import com.example.cursosant.ui.theme.Common_padding_default
import com.example.cursosant.ui.theme.Common_padding_min
import com.example.cursosant.ui.theme.Common_padding_nano
import com.example.cursosant.ui.theme.Common_padding_x_large
import com.example.cursosant.ui.theme.Typography
import com.example.cursosant.ui.theme.msgVerticalSpace
import com.example.cursosant.weather.viewmodel.WeatherUiState
import com.example.cursosant.weather.viewmodel.WeatherViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
private fun WeatherInfoView(weatherCity: WeatherCity){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Common_padding_default),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "${weatherCity.temp_c.toInt()} ℃",
            style = Typography.displayLarge
        )
        Text(
            text = weatherCity.component4(),
            style = Typography.headlineLarge
        )
        Text(
            text = weatherCity.component5(),
            style = Typography.bodyLarge
        )
        AntCoilImage(
            url = weatherCity.component6(),
            modifier = Modifier
                .size(Common_padding_x_large)
                .padding(top = Common_padding_min)
        )

        Text(
            text = weatherCity.component2(),
            style = Typography.headlineSmall,
            textAlign = TextAlign.Center
        )

        Text(
            text = if(weatherCity.name.isEmpty()) "" else "${weatherCity.component5()} km/h",
            style = Typography.bodyLarge
        )
    }
}

@Composable
private fun WeatherSearchView(onSearch: (String) -> Unit){

    var cityValue by remember { mutableStateOf("") }

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(top = Common_padding_default),
        horizontalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = cityValue,
            onValueChange = { cityValue = it },
            label = {
                Text(text = stringResource(R.string.cities_hint_search))
            },
        )
        FilledIconButton(
            onClick = { onSearch(cityValue) },
            modifier = Modifier.padding(Common_padding_min)
        ) {
            Icon(Icons.Default.Search, contentDescription = null)
        }
    }
}


@Composable
fun WeatherView(modifier: Modifier = Modifier, vm: WeatherViewModel = koinViewModel()) {
    val uiState by vm.uiState.collectAsState()
    Box() {
        Column(modifier.fillMaxSize()) {
            AntSnackbar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(msgVerticalSpace),
                msgRes = uiState.msgRes,
                onDismiss = {
                    vm.clearMsg()
                }
            )
            AntTextTitle(R.string.weather_title)
            WeatherSearchView { searchCity ->
                vm.searchWeather(searchCity)
            }
            ActionsView(
                uiState,
                onSelect = { city ->
                    vm.getWeatherByCity(city)
                },
                onSave = {
                    vm.saveWeatherCity(uiState.data)
                }
            )
            WeatherInfoView(uiState.data)

        }
        AnimatedVisibility(
            visible = uiState.inProgress,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            AntProgressFullScreen()
        }
    }
}


@Composable
private fun ActionsView(uiState: WeatherUiState, onSelect: (Location) -> Unit, onSave: () -> Unit){

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(top = Common_padding_default),
        horizontalArrangement = Arrangement.Center) {
        AntDropDownMenu(
            items = uiState.items,
            labelRes = R.string.cities_title,
            onSelect = { city ->
                onSelect(city)
            }
        )
        OutlinedButton(
            onClick = {
                onSave()
            },
            modifier = Modifier.padding(Common_padding_nano),
            enabled = uiState.data.name.isNotEmpty()
        ) {
            Icon(Icons.Default.CloudDownload, contentDescription = null)
        }
    }
}




