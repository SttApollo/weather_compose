package com.example.weather_compose.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID


@Entity(tableName = "weather_items")
data class WeatherItem(
    @PrimaryKey @ColumnInfo(name = "id") val weatherItemId: String = UUID.randomUUID().toString(),
    val latitude: Double,
    val longitude: Double,
    val resolvedAddress: String,
    val address: String,
    val temperature: String,
   val tempMax: String,
    val tempMin: String,
    val icon: String,
    val conditions: String
) {
    //Don't really need this for data class
//    override fun toString(): String {
//        return "Weather(address='$resolvedAddress', currentCondition='$conditions', temp='$temperature', tempMax='$tempMax', tempMin='$tempMin', id=$id)"
//    }
}


