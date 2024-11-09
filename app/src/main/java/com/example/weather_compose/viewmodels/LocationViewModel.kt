package com.example.weather_compose.viewmodels

import android.location.Geocoder
import android.location.Location
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather_compose.location.LocationTracker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val locationTracker: LocationTracker,
    private val geocoder: Geocoder,
   ) : ViewModel() {

    //---- get Current location
    // Create a variable that will hold the current location state and it will be updated with the getCurrentLocation function.
    var currentLocation by mutableStateOf<Location?>(null)

    private val _geocodedLocation = MutableStateFlow<Pair<String, String>?>(null)
    val geocodedLocation: StateFlow<Pair<String, String>?> = _geocodedLocation

        fun getCurrentLocation() {
        viewModelScope.launch(Dispatchers.IO) {
            currentLocation = locationTracker.getCurrentLocation() // Location
            currentLocation?.let {
                Log.d("LocationViewModel", "Current location: ${it.latitude}, ${it.longitude}")
                geocodeLocation(it) // Automatically geocode location when obtained
            }?: Log.e("LocationViewModel", "Failed to retrieve location")
        }

    }// end of fun getCurrentLocation()

    // Function to geocode the location
    private fun geocodeLocation(location: Location) {
        geocoder.getFromLocation(
            location.latitude,
            location.longitude,
            1,
            object : Geocoder.GeocodeListener {
                override fun onGeocode(addresses: List<android.location.Address>) {
                    if (addresses.isNotEmpty()) {
                        val city = addresses[0].locality ?: ""
                        val country = addresses[0].countryName ?: ""
                        _geocodedLocation.value = Pair(city, country)
                        Log.d("LocationViewModel", "Geocoded location: $city, $country")

                    } else {
                        Log.e("LocationViewModel", "Geocoder returned empty address list.")
                    }
                }

                override fun onError(errorMessage: String?) {
                    Log.e("LocationViewModel", "Geocoding error: $errorMessage")
                }
            }
        )
    }
}
