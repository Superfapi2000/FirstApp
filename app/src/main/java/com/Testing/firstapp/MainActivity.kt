package com.Testing.firstapp

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    private val forecastRepository = ForecastRepository()

// region Setup methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    val zipcodeEditText : EditText = findViewById(R.id.ZipCodeEditText)
    val enterButton: Button = findViewById(R.id.EnterButton)

    enterButton.setOnClickListener{it
        val ZipCode : String = zipcodeEditText.text.toString()
        if (ZipCode.length != 4) {
            Toast.makeText(this, R.string.zip_code_error, Toast.LENGTH_SHORT).show()
        }
        else
            forecastRepository.loadForecast(ZipCode)
     }
    val forecastList: RecyclerView = findViewById(R.id.forecastList)
    forecastList.layoutManager = LinearLayoutManager(this)
    val dailyForecastAdapter = DailyForecastAdapter() {
        val msg = getString(R.string.foreCast_Format,it.temp,it.description)
    Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()

    }
    forecastList.adapter = dailyForecastAdapter
    val weeklyForecastObserver = Observer<List<DailyForecast>>{ forecastItems ->
        dailyForecastAdapter.submitList(forecastItems)
    }
    forecastRepository.weeklyForecast.observe(this , weeklyForecastObserver)
    }
}

 /**   override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

// endregion Setup methods

// region Teardown methods
    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()}
// endregion Teardown Methods

}**/