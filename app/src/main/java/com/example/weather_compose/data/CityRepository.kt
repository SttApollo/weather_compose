package com.example.weather_compose.data

import android.content.Context
import android.util.Log
import com.example.weather_compose.models.City
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

// Interface version of CityRepository
interface CityRepository {


    suspend fun loadCitiesFromAssets()
    suspend fun searchCities(cityQuery: String, countryQuery: String): List<City>
}


@Singleton
class CityRepositoryImpl @Inject constructor(@ApplicationContext private val context: Context)
    : CityRepository{

    // Using a normal mutable list with synchronization
    val cityList = mutableListOf<CityResponse>()

    // Secondary constructor for testing
    constructor(testContext: Context, testCityList: MutableList<CityResponse> = mutableListOf()) : this(testContext) {
        cityList.addAll(testCityList) // Initialize cityList with test data if provided
    }

      // Function to load cities from the assets folder
      override suspend fun loadCitiesFromAssets() {
          // Switch to IO context for performing file operations
          withContext(Dispatchers.IO) {

              // Open the cities.json file from the assets as an InputStream
              context.assets.open("cities.json").use { inputStream ->

                  // Wrap the InputStream in a BufferedReader to enable line-by-line reading
                  inputStream.bufferedReader().use { bufferedReader ->

                      // Use JsonReader to parse the JSON file, assuming it contains an array of city objects
                      JsonReader(bufferedReader).use { jsonReader ->

                          // Begin parsing the JSON array
                          jsonReader.beginArray()
                          val gson = Gson()  // Gson instance for JSON deserialization

                          // Read each JSON object within the array
                          while (jsonReader.hasNext()) {
                              // Deserialize each JSON object in the array to a CityResponse object
                              val city = gson.fromJson<CityResponse>(jsonReader, CityResponse::class.java)

                              // Add the deserialized CityResponse object to the cityList
                              cityList.add(city)
                          }

                          // End parsing of the JSON array
                          jsonReader.endArray()
                      }
                  }
              }

              // Log the number of cities loaded for debugging purposes
              Log.d("CityRepository", "Loaded ${cityList.size} cities from assets")
          }
      }


    // Suspend function to search cities by name
    override suspend fun searchCities(cityQuery: String, countryQuery: String): List<City> {
        return withContext(Dispatchers.IO) {
            synchronized(cityList) {
                //Log.d("CityRepository", "Loaded city list size: ${cityList.size}")
                //Log.d("CityRepository", "Searching for city: $cityQuery, country: $countryQuery")

                val filteredCities = cityList.toList().filter { cityResponse ->
                    cityResponse.name.contains(cityQuery, ignoreCase = true) &&
                            (countryQuery.isEmpty() || cityResponse.country_name.contains(countryQuery, ignoreCase = true))
                }

                //Log.d("CityRepository", "Filtered city responses: $filteredCities")

                val cityResults = filteredCities.map { cityResponse ->
                    City(
                        name = cityResponse.name,
                        lat = cityResponse.latitude.toDouble(),
                        lon = cityResponse.longitude.toDouble(),
                        country = cityResponse.country_name
                    )
                }

                //Log.d("CityRepository", "Mapped city results: $cityResults")
                return@withContext cityResults
            }
        }
    }//end of searchCities

}
