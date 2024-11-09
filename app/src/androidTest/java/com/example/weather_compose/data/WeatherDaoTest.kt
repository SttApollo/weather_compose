package com.example.weather_compose.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.weather_compose.utilities.testWeatherItems
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WeatherDaoTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    private lateinit var database: AppDatabase
    private lateinit var weatherDao: WeatherDao



    @Before
    fun createDb() = runTest {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        weatherDao = database.weatherDao()

        database.weatherDao().upsertAll(testWeatherItems)
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testDeleteWeatherItem() = runTest {
        val weatherItemToDelete = testWeatherItems[0]
        weatherDao.deleteWeatherItem(weatherItemToDelete)

        val remainingItems = weatherDao.getAllWeatherItems()
        assertEquals("Expected ${testWeatherItems.size - 1} items, but got ${remainingItems.size}", testWeatherItems.size - 1, remainingItems.size)
        assertFalse("Deleted weather item with ID ${weatherItemToDelete.weatherItemId} should not exist", remainingItems.any { it.weatherItemId == weatherItemToDelete.weatherItemId })
    }

    @Test
    fun testDeleteAllWeatherItems() = runTest {
        weatherDao.deleteAllWeatherItems()
        val weatherItems = weatherDao.getAllWeatherItems()
        assertTrue("Expected no weather items, but got ${weatherItems.size}", weatherItems.isEmpty())
    }

    @Test fun testGetWeatherItems() = runTest {
        val weatherItems = weatherDao.getAllWeatherItems()
        assertTrue("Weather items list should not be empty", weatherItems.isNotEmpty())
        assertTrue("Expected ${testWeatherItems.size} items, but got ${weatherItems.size}", weatherItems.size == testWeatherItems.size)

    }

    @Test fun testGetWeatherItemByAddress() = runTest {
        val weatherItem = weatherDao.getWeatherItemByAddress("San Francisco, US")
        assertNotNull("Expected to find weather item for San Francisco, US", weatherItem)
        assertEquals("Expected ${testWeatherItems[0]}, but got $weatherItem", testWeatherItems[0], weatherItem)
    }



}








