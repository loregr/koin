package org.koin.sampleapp.weather

import junit.framework.Assert
import org.junit.Before
import org.junit.Test
import org.koin.sampleapp.di.RxTestModule
import org.koin.sampleapp.di.WeatherModule
import org.koin.sampleapp.di.testRemoteDatasource
import org.koin.sampleapp.util.any
import org.koin.test.components.KoinTest
import org.koin.test.components.getKoin
import org.koin.test.components.inject
import org.koin.test.components.startContext
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class WeatherPresenterTest : KoinTest {

    val presenter by inject<WeatherContract.Presenter>()
    @Mock lateinit var view: WeatherContract.View

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        startContext(testRemoteDatasource())

        // inject server property
        getKoin().setProperty(WeatherModule.SERVER_URL, RxTestModule.SERVER_URL)

        presenter.view = view
    }

    @Test
    fun testDisplayWeather() {
        Assert.assertNotNull(presenter)

        val locationString = "Paris, france"
        presenter.getWeather(locationString)

        Mockito.verify(view).displayWeather(any(), any())
    }

}