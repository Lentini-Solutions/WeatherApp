package com.example.cursosant.cities.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cursosant.R
import com.example.cursosant.cities.model.LocalDatabase
import com.example.cursosant.common.entities.Location
import com.example.cursosant.common.utils.IntentUtils
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CitiesViewModel(
    private val ldb: LocalDatabase, private val utils: IntentUtils
): ViewModel(), ICitiesViewModel {

    private val _uiState = MutableStateFlow(CityUiState())
    override fun getUiState(): StateFlow<CityUiState> = _uiState.asStateFlow()

    init {
        getRealTimeAllCities()
    }

    private fun getRealTimeAllCities() {
        viewModelScope.launch {
            ldb.getRealTimeAllCities().collect { result ->
                if (result.isNotEmpty()) {
                    _uiState.update { it.copy(items = result) }
                } else {
                    _uiState.update {
                        it.copy(
                            items = emptyList(),
                            msgRes = R.string.cities_msg_empty_list
                        )
                    }
                }
            }
        }
    }

    override fun showMap(city: Location) {
        utils.showMap(city.lat, city.lon, city.name)
    }

    override fun clearMsg() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(msgRes = R.string.empty_msg)
            }
        }
    }

    override fun deleteCity(city: Location) {
        executeAction {
            ldb.deleteCityAndWeather(city){ result ->
                if(result){
                    _uiState.update { it.copy(msgRes = R.string.city_weather_delete_success) }
                }else{
                    _uiState.update { it.copy(msgRes = R.string.city_weather_delete_error) }
                }
            }
        }
    }

    private fun executeAction(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            _uiState.update {
                it.copy(inProgress = true)
            }
            try {
                block()
            }catch (_: Exception){
                _uiState.update {
                    it.copy(msgRes = R.string.weather_general_error)
                }
            }finally {
                _uiState.update {
                    it.copy(inProgress = false)
                }
            }
        }
    }

}