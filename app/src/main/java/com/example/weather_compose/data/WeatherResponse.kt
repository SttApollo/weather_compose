package com.example.weather_compose.data

import com.google.gson.annotations.SerializedName


data class WeatherResponse(
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("resolvedAddress") val resolvedAddress: String,
    @SerializedName("address") val address: String,
    @SerializedName("timezone") val timezone: String,
    @SerializedName("description") val description: String,
    @SerializedName("days") val days: List<DailyForecastResponse>
)

data class DailyForecastResponse(
    @SerializedName("datetime") val date: String,
    @SerializedName("datetimeEpoch") val datetimeEpoch: Long? = null,
    @SerializedName("tempmax") val tempMax: Double? = null,
    @SerializedName("tempmin") val tempMin: Double? = null,
    @SerializedName("temp") val temp: Double? = null,
    @SerializedName("feelslikemax") val feelsLikeMax: Double? = null,
    @SerializedName("feelslikemin") val feelsLikeMin: Double? = null,
    @SerializedName("feelslike") val feelsLike: Double? = null,
    @SerializedName("dew") val dew: Double? = null,
    @SerializedName("humidity") val humidity: Double? = null,
    @SerializedName("precip") val precip: Double? = null,
    @SerializedName("precipprob") val precipProb: Double? = null,
    @SerializedName("precipcover") val precipCover: Double? = null,
    @SerializedName("preciptype") val precipType: List<String>? = null,
    @SerializedName("snow") val snow: Double? = null,
    @SerializedName("snowdepth") val snowDepth: Double? = null,
    @SerializedName("windgust") val windGust: Double? = null,
    @SerializedName("windspeed") val windSpeed: Double? = null,
    @SerializedName("winddir") val windDir: Double? = null,
    @SerializedName("pressure") val pressure: Double? = null,
    @SerializedName("cloudcover") val cloudCover: Double? = null,
    @SerializedName("visibility") val visibility: Double? = null,
    @SerializedName("solarradiation") val solarRadiation: Double? = null,
    @SerializedName("solarenergy") val solarEnergy: Double? = null,
    @SerializedName("uvindex") val uvIndex: Int? = null,
    @SerializedName("severerisk") val severeRisk: Int? = null,
    @SerializedName("sunrise") val sunrise: String? = null,
    @SerializedName("sunriseEpoch") val sunriseEpoch: Long? = null,
    @SerializedName("sunset") val sunset: String? = null,
    @SerializedName("sunsetEpoch") val sunsetEpoch: Long? = null,
    @SerializedName("moonphase") val moonPhase: Double? = null,
    @SerializedName("conditions") val conditions: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("icon") val icon: String? = null,
    @SerializedName("stations") val stations: List<String>? = null,
    @SerializedName("source") val source: String? = null,
    @SerializedName("hours") val hours: List<HourlyForecastResponse>? = null
)


data class HourlyForecastResponse(
    @SerializedName("datetime") val time: String,
    @SerializedName("datetimeEpoch") val datetimeEpoch: Long,
    @SerializedName("temp") val temperature: Double,
    @SerializedName("feelslike") val feelsLike: Double,
    @SerializedName("humidity") val humidity: Double,
    @SerializedName("dew") val dew: Double,
    @SerializedName("precip") val precip: Double,
    @SerializedName("precipprob") val precipProb: Double,
    @SerializedName("snow") val snow: Double,
    @SerializedName("snowdepth") val snowDepth: Double,
    @SerializedName("preciptype") val precipType: List<String>?,
    @SerializedName("windgust") val windGust: Double,
    @SerializedName("windspeed") val windSpeed: Double,
    @SerializedName("winddir") val windDir: Double,
    @SerializedName("pressure") val pressure: Double,
    @SerializedName("visibility") val visibility: Double,
    @SerializedName("cloudcover") val cloudCover: Double,
    @SerializedName("solarradiation") val solarRadiation: Double,
    @SerializedName("solarenergy") val solarEnergy: Double,
    @SerializedName("uvindex") val uvIndex: Int,
    @SerializedName("severerisk") val severeRisk: Int,
    @SerializedName("conditions") val conditions: String,
    @SerializedName("icon") val icon: String,
    @SerializedName("stations") val stations: List<String>?,
    @SerializedName("source") val source: String
)
