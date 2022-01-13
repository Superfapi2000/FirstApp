package com.Testing.firstapp

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import kotlin.random.Random

class ForecastRepository {
    private  val _weeklyForecast = MutableLiveData<List<DailyForecast>>() //for change
    val weeklyForecast: LiveData<List<DailyForecast>> = _weeklyForecast//ativity can get acees

    fun loadForecast (zipcode :String) {
        val randomValues = List(7) { (0..45).random().toFloat() }
        val forecastItems = randomValues.map{
            DailyForecast(it,getDescription(it))
        }
        _weeklyForecast.setValue(forecastItems)
    }
    private fun getDescription( temp :Float ) : String{
        return when(temp) {
            in Float.MIN_VALUE.rangeTo(0f) -> "You done frezing"
            in 0f.rangeTo(10f) -> "You thought going to Porto on Winter was okay?StupidAss.."
            in 10f.rangeTo(20f) -> "Griponal stonks never been higher"
            in 20f.rangeTo(30f) -> "In Algarve Assating Chouriza"
            else -> "Bruh you just dead"

        }
    }
}