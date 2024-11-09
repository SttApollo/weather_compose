package com.example.weather_compose.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.weather_compose.data.CityRepository
import com.example.weather_compose.models.City
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class CitySearchViewModel @Inject constructor(
    private val cityRepository: CityRepository
) : ViewModel(), ViewModelProvider.Factory {
    private val _cities = MutableStateFlow<ImmutableList<City>>(persistentListOf())
    val cities: StateFlow<ImmutableList<City>> = _cities
    //var debounceJob: Job? = null

    init {
        viewModelScope.launch {
            try {
                // Load cities from assets first
                cityRepository.loadCitiesFromAssets()
                //Log.d("CitySearchViewModel", "Cities loaded from assets")

                // Wait for the cities to be loaded, then search with an empty query to get all cities
                val allCities = cityRepository.searchCities("", "")

                // Check if the result is not empty before assigning it to the StateFlow
                if (allCities.isNotEmpty()) {
                    Log.d("CitySearchViewModel", "Found ${allCities.size} matching cities.")
                    //Log.d("CitySearchViewModel", "Initial cities loaded: $allCities")
                    _cities.value = allCities.toImmutableList()
                } else {
                    Log.e("CitySearchViewModel", "No cities loaded from the repository!")
                }
            } catch (e: Exception) {
                Log.e("CitySearchViewModel", "Error initializing CitySearchViewModel", e)
            }
        }
    }

    // Search cities based on query
    fun searchCities(query: String) {
        //debounceJob?.cancel()
        //debounceJob =
        viewModelScope.launch {
            //delay(100) // Debounce for 100 milliseconds
            try {
                // Get the search results from the repository
                val result = withContext(Dispatchers.Default){
                    val parts = query.split(",") // Split query into city and country
                    val cityQuery = parts.getOrNull(0)?.trim() ?: ""
                    val countryQuery = parts.getOrNull(1)?.trim() ?: ""

                    cityRepository.searchCities(cityQuery, countryQuery).take(3) // Pass both to repository
                }
                // Update the _cities state flow with the search results
                _cities.value = result.toImmutableList()
                // Log the search results
                //Log.d("CitySearchViewModel", "Cities: $result")
            } catch (e: Exception) {
                // Log any errors that occur during the search
                Log.e("CitySearchViewModel", "Error searching cities", e)
            }
        }
    }//end of searchCities


}
