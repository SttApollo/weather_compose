package com.example.weather_compose.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "addresses")
data class Address(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val city: String,
    val country: String
)