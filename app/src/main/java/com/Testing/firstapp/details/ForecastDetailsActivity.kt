package com.Testing.firstapp.details

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import com.Testing.firstapp.*

class ForecastDetailsActivity : AppCompatActivity() {

    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast_details)
        tempDisplaySettingManager = TempDisplaySettingManager(this )

        setTitle(R.string.foreCastDetails) //metedo serve para escolher um titulo para a atividade

        val tempText = findViewById<TextView>(R.id.tempview)
        val tempDescription = findViewById<TextView>(R.id.tempDescription)

        val temp = intent.getFloatExtra("key_temp",0f)

        tempText.text = formatTempForDisplay(temp, tempDisplaySettingManager.getTempDisplaySetting())
        tempDescription.text=  intent.getStringExtra("key_description")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater :MenuInflater = menuInflater
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