package com.Testing.firstapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class MainActivity : AppCompatActivity() {
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
            Toast.makeText(this, ZipCode, Toast.LENGTH_SHORT).show()
     }
        }

    override fun onStart() {
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

}