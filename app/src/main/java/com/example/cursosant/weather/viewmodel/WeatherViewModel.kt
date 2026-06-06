package com.example.cursosant.weather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cursosant.R
import com.example.cursosant.common.entities.Location
import com.example.cursosant.common.entities.WeatherCity
import com.example.cursosant.common.utils.NetworkUtils
import com.example.cursosant.weather.domain.DataSource
import com.example.cursosant.weather.model.LocalDatabase
import com.example.cursosant.weather.model.RemoteDatabase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val ds: DataSource
): ViewModel() {
    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState: StateFlow<WeatherUiState> = _uiState

    init {
        getAllCities()
    }

    private fun getAllCities(){
        executeAction {
            ds.getAllCities { cities ->
                _uiState.update {
                    it.copy(items = cities)
                }
            }
        }
    }

    fun searchWeather(name: String){
        executeAction {
            ds.searchWeatherByName(name = name){ result ->
                if(result != null){
                    _uiState.update {
                        it.copy(data = result)
                    }
                }else{
                    _uiState.update {
                        it.copy(msgRes = R.string.weather_search_error)
                    }
                }
            }
        }
    }

    fun saveWeatherCity(weatherCity: WeatherCity){
        executeAction {
            ds.addWeatherAndCity(weatherCity){ isSuccess ->
                if(isSuccess){
                    _uiState.update {
                        it.copy(msgRes = R.string.local_save_success)
                    }
                }else{
                    _uiState.update {
                        it.copy(msgRes = R.string.local_save_error)
                    }
                }
            }
        }
    }

    fun clearMsg(){
        viewModelScope.launch {
            _uiState.update {
                it.copy(msgRes = R.string.empty_msg)
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

    fun getWeatherByCity(city: Location){
        executeAction {
            ds.getWeatherByCity(city) { result ->
                if (result != null) {
                    _uiState.update {
                        it.copy(data = result)
                    }
                } else {
                    _uiState.update {
                        it.copy(msgRes = R.string.weather_local_by_city_error)
                    }
                }
            }
        }
    }
}