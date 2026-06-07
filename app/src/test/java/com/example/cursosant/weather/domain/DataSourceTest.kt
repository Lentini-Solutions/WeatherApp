package com.example.cursosant.weather.domain

import com.example.cursosant.common.entities.WeatherCity
import com.example.cursosant.common.model.cityNullIslandTest
import com.example.cursosant.common.model.weatherCityNullIslandTest
import com.example.cursosant.common.utils.FormatUtils
import com.example.cursosant.common.utils.NetworkUtils
import com.example.cursosant.weather.model.LocalDatabase
import com.example.cursosant.weather.model.RemoteDatabase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject

@OptIn(ExperimentalCoroutinesApi::class)
class DataSourceTest: KoinTest {

    private val rdb: RemoteDatabase = mockk()
    private val ldb: LocalDatabase = mockk()
    private val ntw: NetworkUtils = mockk()
    private val fts: FormatUtils = mockk()
    private val ds: DataSource by inject()

    private val testDispatcher = UnconfinedTestDispatcher(TestCoroutineScheduler())

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        startKoin {
            modules(modules = module {
                single { DataSource(get(), get(), get()) }
                single { rdb }
                single { ldb }
                single { ntw }
                single { fts }
            })
        }
    }

    @After
    fun tearDown() {
        stopKoin()
        Dispatchers.resetMain()
    }

    @Test
    fun getWeatherByCityOnlineSuccess() = runTest {

        val city = cityNullIslandTest
        val expectedWeatherCity = weatherCityNullIslandTest

        every { ntw.isOnline() } returns true
        coEvery {
            rdb.getWeatherByCoordinates("${city.lat},${city.lon}", any())
        } coAnswers {
            secondArg<(WeatherCity?) -> Unit>().invoke(expectedWeatherCity)
        }

        var result: WeatherCity? = null
        ds.getWeatherByCity(city) { weatherCity ->
            if(weatherCity != null){
                result = weatherCity
            }

        }

        //should be called 1 time
        coVerify (exactly = 1) {
            rdb.getWeatherByCoordinates(
                coordinates = "${city.lat},${city.lon}",
                onResult = any()
            )
        }

        //should be called 1 time
        coVerify (exactly = 0) {
            ldb.getWeatherCityById(
                cityId = city.id,
                onResult = any()
            )
        }

        assertEquals(expectedWeatherCity, result)
    }

    @Test
    fun getWeatherByCityOfflineSuccess() = runTest {

        val city = cityNullIslandTest
        val expectedWeatherCity = weatherCityNullIslandTest

        every { ntw.isOnline() } returns false
        coEvery {
            ldb.getWeatherCityById(city.id, any())
        } coAnswers {
            secondArg<(WeatherCity?) -> Unit>().invoke(expectedWeatherCity)
        }

        var result: WeatherCity? = null
        ds.getWeatherByCity(city) { weatherCity ->
            if(weatherCity != null){
                result = weatherCity
            }

        }

        //should be called 1 time
        coVerify (exactly = 0) {
            rdb.getWeatherByCoordinates(
                coordinates = "${city.lat},${city.lon}",
                onResult = any()
            )
        }

        //should be called 1 time
        coVerify (exactly = 1) {
            ldb.getWeatherCityById(
                cityId = city.id,
                onResult = any()
            )
        }

        assertEquals(expectedWeatherCity, result)
    }

    @Test
    fun getWeatherByCityOnlineFails() = runTest {

        val city = cityNullIslandTest

        every { ntw.isOnline() } returns true
        coEvery {
            rdb.getWeatherByCoordinates("${city.lat},${city.lon}", any())
        } coAnswers {
            secondArg<(WeatherCity?) -> Unit>().invoke(null)
        }

        var result: WeatherCity? = WeatherCity()
        ds.getWeatherByCity(city) { weatherCity ->
            result = weatherCity
        }

        //should be called 1 time
        coVerify (exactly = 1) {
            rdb.getWeatherByCoordinates(
                coordinates = "${city.lat},${city.lon}",
                onResult = any()
            )
        }

        //should be called 1 time
        coVerify (exactly = 0) {
            ldb.getWeatherCityById(
                cityId = city.id,
                onResult = any()
            )
        }

        assertNull(result)
    }

    @Test
    fun getWeatherByCityOfflineFails() = runTest {

        val city = cityNullIslandTest

        every { ntw.isOnline() } returns false
        coEvery {
            ldb.getWeatherCityById(cityId = city.id, any())
        } coAnswers {
            secondArg<(WeatherCity?) -> Unit>().invoke(null)
        }

        var result: WeatherCity? = WeatherCity()
        ds.getWeatherByCity(city) { weatherCity ->
            result = weatherCity
        }

        //should be called 1 time
        coVerify (exactly = 0) {
            rdb.getWeatherByCoordinates(
                coordinates = "${city.lat},${city.lon}",
                onResult = any()
            )
        }

        //should be called 1 time
        coVerify (exactly = 1) {
            ldb.getWeatherCityById(
                cityId = city.id,
                onResult = any()
            )
        }

        assertNull(result)
    }

    @Test
    fun searchWeatherByNameEmptyReturnsNull() = runTest{

        val name = ""

        coEvery {
            rdb.searchWeatherByName(name = name, any())
        } coAnswers {
            secondArg<(WeatherCity?) -> Unit>().invoke(null)
        }

        var result: WeatherCity? = WeatherCity()
        ds.searchWeatherByName(name) { weatherCity ->
            result = weatherCity
        }

        //should be called 1 time
        coVerify (exactly = 1) {
            rdb.searchWeatherByName(name = name, any())
        }

        assertNull(result)

    }
}
