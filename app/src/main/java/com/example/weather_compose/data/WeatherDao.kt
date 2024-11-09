package com.example.weather_compose.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.weather_compose.models.Address
import com.example.weather_compose.models.WeatherItem

@Dao
interface WeatherDao {

    //Address
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAddress(address: Address)

    @Query("SELECT * FROM addresses WHERE city = :city AND country = :country LIMIT 1")
    suspend fun getAddress(city: String, country: String): Address?

    @Query("SELECT * FROM addresses")
    suspend fun getAllAddresses(): List<Address>

    @Query("DELETE FROM addresses")
    suspend fun deleteAllAddresses()

    //WeatherItems
    @Upsert
    suspend fun upsertAll(weatherItems: List<WeatherItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllWeatherItems(weatherItems: List<WeatherItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherItem(weatherItem: WeatherItem)

    @Query("SELECT * FROM weather_items")
    suspend fun getAllWeatherItems(): List<WeatherItem>

    @Query("SELECT * FROM weather_items WHERE address = :address LIMIT 1")
    suspend fun getWeatherItemByAddress(address: String): WeatherItem?

    @Delete
    suspend fun deleteWeatherItem(weatherItem: WeatherItem)

    @Query("DELETE FROM weather_items")
    suspend fun deleteAllWeatherItems()

    @Query("DELETE FROM addresses WHERE city = :city AND country = :country")
    suspend fun deleteAddress(city: String, country: String)
}

