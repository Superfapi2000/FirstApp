package com.Testing.firstapp

import android.content.Context
import android.support.v7.app.AlertDialog
import android.widget.Toast

fun formatTempForDisplay(temp : Float,tempDisplaySetting: TempDisplaySetting) : String {
    return when (tempDisplaySetting) {
        TempDisplaySetting.Celsius -> String.format("%.0f Cº",temp)
        TempDisplaySetting.Fahrenheit -> {
            val temp = (temp *1.8f) + 32
            String.format("%.2f Fº",temp)
        }
    }

}
fun showTempDisplaySettingDialog(context: Context,tempDisplaySettingManager:TempDisplaySettingManager){
    val dialogBuilder = AlertDialog.Builder(context)
    dialogBuilder.setTitle("Chose Display Units")
        .setMessage("Chose the temperature to display")
        .setPositiveButton("Fº"){_,_  ->
            tempDisplaySettingManager.updateSettings(TempDisplaySetting.Fahrenheit)
        }
        .setNeutralButton("cº"){_,_  ->
            tempDisplaySettingManager.updateSettings(TempDisplaySetting.Celsius)
        }
        .setOnDismissListener{
            Toast.makeText(context, "Setting will be Upgraded when you Restart the App", Toast.LENGTH_SHORT).show()
        }
    dialogBuilder.show()
}