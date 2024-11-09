package com.example.weather_compose.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather_compose.BuildConfig
import com.example.weather_compose.data.WeatherRepository
import com.example.weather_compose.models.WeatherItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherItemListViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {
    private val _weatherItems = MutableStateFlow<List<WeatherItem>>(emptyList())
    val weatherItems: StateFlow<List<WeatherItem>> = _weatherItems

    init {
        // on ViewModel initialization
        viewModelScope.launch {
            refreshWeatherItems()
        }
    }


    fun refreshWeatherItems() {
        viewModelScope.launch {
            try {
                // Fetch and update weather items with fresh data
                weatherRepository.updateWeatherItemsWithFreshData(BuildConfig.VISUAL_CROSSING_API_KEY)
                // Get the updated weather items
                val updatedItems = weatherRepository.getAllWeatherItems()
                _weatherItems.value = updatedItems
            } catch (e: Exception) {
                Log.e("WeatherItemListViewModel", "Error refreshing weather items", e)
            }
        }
    } //end of refreshWeatherItems

    fun deleteWeatherItem(weatherItem: WeatherItem) {
        viewModelScope.launch {try {
            weatherRepository.deleteWeatherItem(weatherItem)
            // Update the state immediately after deletion
            val updatedList = _weatherItems.value.toMutableList()
            updatedList.remove(weatherItem)
            _weatherItems.value = updatedList // Emit a new list to trigger recomposition
        } catch (e: Exception) {
            Log.e("WeatherItemListViewModel", "Error deleting weather item", e)
        }
        }
    }

    private fun deleteAllWeatherItems() {
        viewModelScope.launch {
            try {
                weatherRepository.deleteAllWeatherItems()
                refreshWeatherItems()
            } catch (e: Exception) {
                Log.e("WeatherItemListViewModel", "Error deleting all weather items", e)
            }
        }
    }


}

