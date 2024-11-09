package com.example.weather_compose.data

import android.util.Log
import com.example.weather_compose.api.WeatherService
import com.example.weather_compose.models.Address
import com.example.weather_compose.models.WeatherItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(
    private val weatherService: WeatherService,
    private val weatherDao: WeatherDao
) {
    suspend fun getWeather(city: String, country: String, apiKey: String): WeatherResponse {
        return try {
            val response = weatherService.getWeather(city, country, apiKey)
            Log.d("WeatherRepository", "Fetched weather: $response")
            response
        } catch (e: Exception) {
            Log.e("WeatherRepository", "Error fetching weather: ${e.message}", e)
            throw e
        }
    }

//   suspend fun saveWeatherItem(weatherItem: WeatherItem) {
//        weatherDao.insertWeatherItem(weatherItem)
//    }

    //Address
    suspend fun saveAddress(address: Address) {
        Log.d("WeatherRepository", "Saving address: $address")
        weatherDao.insertAddress(address)
        val addresses = getAllAddresses()
        Log.d("WeatherRepository", "All saved addresses after insertion: $addresses")
    }

    suspend fun getAllAddresses(): List<Address> {
        Log.d("WeatherRepository", "Fetching all addresses")
        val addresses = weatherDao.getAllAddresses()
        Log.d("WeatherRepository", "Fetched addresses: $addresses")
        return addresses
    }

    suspend fun deleteAddressByCityAndCountry(city: String, country: String) {
        weatherDao.deleteAddress(city, country)
    }

    suspend fun deleteAllAddresses() {
        weatherDao.deleteAllAddresses()
    }

    suspend fun isAddressSaved(city: String, country: String): Boolean {
        val address = weatherDao.getAddress(city, country)
        return address != null
    }


    //WeatherItems

    suspend fun getAllWeatherItems(): List<WeatherItem> {
        return weatherDao.getAllWeatherItems()
    }


    suspend fun updateWeatherItemsWithFreshData(apiKey: String) {
        val addresses = getAllAddresses()
        val freshWeatherItems = addresses.mapNotNull { address ->
            try {
                val weather = getWeather(address.city, address.country, apiKey)
                weather.days[0].icon?.let {
                    weather.days[0].conditions?.let { it1 ->
                        WeatherItem(
                            latitude = weather.latitude,
                            longitude = weather.longitude,
                            resolvedAddress = weather.resolvedAddress,
                            address = "${address.city}, ${address.country}",
                            temperature = weather.days[0].temp.toString(),
                            tempMax = weather.days[0].tempMax.toString(),
                            tempMin = weather.days[0].tempMin.toString(),
                            icon = it,
                            conditions = it1
                        )
                    }
                }

            } catch (e: Exception) {
                Log.e("WeatherRepository", "Error updating weather data for saved addresses: $addresses", e)
                null
            }
        }
        weatherDao.deleteAllWeatherItems()
        weatherDao.insertAllWeatherItems(freshWeatherItems)
        Log.d("WeatherRepository -insertAllWeatherItems", "$freshWeatherItems")
    }//end of update

    suspend fun deleteWeatherItem(weatherItem: WeatherItem) {
        weatherDao.deleteWeatherItem(weatherItem)
        deleteAddressByCityAndCountry(weatherItem.address.split(",")[0].trim(), weatherItem.address.split(",")[1].trim())
    }

    suspend fun deleteAllWeatherItems() {
        weatherDao.deleteAllWeatherItems()
        deleteAllAddresses()
    }
}
