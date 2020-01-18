package com.roland.howtoandroid

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    // This stores the string name of the city we've selected
    // In order to make sure we've selected something,
    // This value should be updated automatically to match the default Spinner value
    private var selectedCity: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        // Reference the spinner and set a callback
        val spinner: Spinner = findViewById(R.id.spinner)
        spinner.onItemSelectedListener = this

        // Preset the button to do something on click
        // In our case, we'll want it to open the next screen
        // using our selected city name
        primaryButton.setOnClickListener { view ->
            // Create an intent to open the next activity
            val intent = Intent(this, WeatherListActivity::class.java).apply {
                // Pass in the required parameter, using the city we have selected
                putExtra(WeatherListActivity.PARAM_CITY_NAME, selectedCity)
            }
            startActivity(intent)
        }
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
        // An item was selected. We'll grab the string name here
         selectedCity = parent.getItemAtPosition(pos) as String
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
        // We don't need to use this for our implementation, so we'll leave it empty
        // However, as AdapterView.OnItemSelectedListener is an interface, we HAVE to declare this
        // function
    }
}
