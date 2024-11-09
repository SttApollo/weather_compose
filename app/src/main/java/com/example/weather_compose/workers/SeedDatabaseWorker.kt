package com.example.weather_compose.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.weather_compose.data.AppDatabase
import com.example.weather_compose.models.WeatherItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStreamReader

class SeedDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val filename = inputData.getString(KEY_FILENAME)
            if (filename != null) {
                applicationContext.assets.open(filename).use { inputStream ->
                    InputStreamReader(inputStream).use { jsonReader ->
                        val weatherType = object : TypeToken<List<WeatherItem>>() {}.type
                        val weatherItemList: List<WeatherItem> = Gson().fromJson(jsonReader, weatherType)

                        val database = AppDatabase.getInstance(applicationContext)
                        database.weatherDao().insertAllWeatherItems(weatherItemList)

                        Result.success()
                    }
                }
            } else {
                Log.e(TAG, "Error seeding database - no valid filename")
                Result.failure()
            }
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }

    companion object {
        private const val TAG = "SeedDatabaseWorker"
        const val KEY_FILENAME = "CITIES_DATA_FILENAME"
    }
}
