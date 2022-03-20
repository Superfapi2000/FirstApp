package com.Testing.firstapp

import android.arch.lifecycle.Observer
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.Testing.firstapp.details.ForecastDetailsActivity


class MainActivity : AppCompatActivity() {
    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager
    private val forecastRepository = ForecastRepository()

// region Setup methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    tempDisplaySettingManager = TempDisplaySettingManager(this)
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

    val dailyForecastAdapter = DailyForecastAdapter(tempDisplaySettingManager) { forecast ->
        showForecastDetails(forecast)
    }
        forecastList.adapter = dailyForecastAdapter
    val weeklyForecastObserver = Observer<List<DailyForecast>>{ forecastItems ->
        dailyForecastAdapter.submitList(forecastItems)
    }
    forecastRepository.weeklyForecast.observe(this , weeklyForecastObserver)
    }
    private fun showForecastDetails(forecast: DailyForecast) {

        val foreCastintent =  Intent(this, ForecastDetailsActivity::class.java)//diz ao sistema que quando lançado deve começar a nova atividade neste caso quando clico num nome
       foreCastintent.putExtra ("key_temp" , forecast.temp)
        foreCastintent.putExtra ("key_description" , forecast.description)
        startActivity(foreCastintent)

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.settings_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.tempDisplaySettings ->{
                showTempDisplaySettingDialog(this,tempDisplaySettingManager)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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