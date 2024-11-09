package com.example.weather_compose.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather_compose.BuildConfig
import com.example.weather_compose.data.WeatherRepository
import com.example.weather_compose.data.WeatherResponse
import com.example.weather_compose.models.Address
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    // Retrieve city and country from SavedStateHandle
    val city: String = savedStateHandle["city"] ?: ""
    val country: String = savedStateHandle["country"] ?: ""

    private val _weather = MutableStateFlow<WeatherResponse?>(null)
    val weather: StateFlow<WeatherResponse?> = _weather

    // LiveData to track if the address is saved, backed by SavedStateHandle
    private val _isAddressSaved = savedStateHandle.getLiveData("isAddressSaved", false)
    val isAddressSaved: LiveData<Boolean> get() = _isAddressSaved

    init {
        // Save city and country to SavedStateHandle
        savedStateHandle["city"] = city
        savedStateHandle["country"] = country

        // Fetch weather details when the ViewModel is initialized
        getWeather(city, country)
        checkAddressSaved(city, country)
    }

    private fun getWeather(city: String, country: String) {
        viewModelScope.launch {
            try {
                val result = weatherRepository.getWeather(city, country, BuildConfig.VISUAL_CROSSING_API_KEY)
                _weather.value = result
            } catch (e: Exception) {
                Log.e("WeatherDetailViewModel", "Error fetching weather details", e)
            }
        }
    }

    fun checkAddressSaved(city: String, country: String) {
        viewModelScope.launch {
            val isSaved = weatherRepository.isAddressSaved(city, country)
            _isAddressSaved.value = isSaved

            // Save the isAddressSaved state in SavedStateHandle
            savedStateHandle["isAddressSaved"] = isSaved
        }
    }

    fun saveAddress(onAddressSaved: () -> Unit) {
        viewModelScope.launch {
            try {
                val address = Address(city = city, country = country)
                weatherRepository.saveAddress(address)
                _isAddressSaved.value = true

                // Update the SavedStateHandle when address is saved
                savedStateHandle["isAddressSaved"] = true
                onAddressSaved()
            } catch (e: Exception) {
                Log.e("WeatherDetailViewModel", "Error saving address", e)
            }
        }
    }
}
