package com.example.weather_compose.viewmodels

import android.util.Log
import com.example.weather_compose.MainCoroutineRule
import com.example.weather_compose.data.FakeCityRepository
import com.example.weather_compose.di.CityRepositoryModule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject


@HiltAndroidTest
@UninstallModules(CityRepositoryModule::class)
class CitySearchViewModelTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @Inject
    lateinit var fakeCityRepository: FakeCityRepository

    // ViewModel instance
    private lateinit var viewModel: CitySearchViewModel

    @Before
    fun setUp() = runTest {

        hiltRule.inject()

        // Use the injected singleton repository
        viewModel = CitySearchViewModel(fakeCityRepository)

        // Ensure the repository is preloaded only once
        fakeCityRepository.loadCitiesFromAssets()

        // Log to verify setup
        Log.d("CitySearchViewModelTest", "ViewModel and FakeCityRepository initialized for testing.")
    }

    @Test
    // Test case for CitySearchViewModel initialization
    fun `CitySearchViewModel should load cities on initialization`() = runTest {

        // Collect the current value of the cities StateFlow
        val loadedCities = viewModel.cities.value

        // Then verify that cities are loaded
        assertTrue("Expected more than one city",loadedCities.size > 1) // Verify that there are multiple emissions
        //Use assertExpected here and get the objects


    }

    @Test
    //Test verifies that when a geocoded location is provided,
    // the searchCities method is called with the correct query and updates the cities state
    fun `searchCities should update cities based on geocoded location`() = runTest {
        // Given a known geocoded location
        val geocodedLocation = "London"

        // When performing a search operation
        viewModel.searchCities(geocodedLocation)

        // Then collect and verify the updated cities StateFlow
        val collectedCities = viewModel.cities.value

        println("Collected cities after search: $collectedCities")
        assertTrue("Expected non-empty city list", collectedCities.isNotEmpty()) // Ensure that there is at least one emission
        assertTrue("Expected London in the results", collectedCities.any { it.name == "London" }) // Check if London is in the results
    }









}