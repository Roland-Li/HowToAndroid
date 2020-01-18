package com.roland.howtoandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.BasicNetwork
import com.android.volley.toolbox.DiskBasedCache
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.JsonObjectRequest

class ScrollingListScreen : AppCompatActivity() {

    // We'll define a map here of our manually stored coordinates
    private val COORDINATE_MAP = listOf(
        "Waterloo" to Pair(0, 0),
        "Kitchener" to Pair(0, 0),
        "Markham" to Pair(0, 0),
        "Ajax" to Pair(0, 0),
        "Toronto" to Pair(0, 0)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling_list_screen)

        // Grab the data that was passed to us
        val cityName = intent.getStringExtra(PARAM_CITY_NAME)

        // In order to verify that we're grabbing the data correctly, we can use a Log!
        // It's good practice to do this while you're developing to make sure nothing is broken
        // Just remember to clean up afterwards!
        // Log.e("Debugging scroll list", cityName)

        // Now that we have everything, let's run our request to get the data
        requestData()
    }

    // Let's cleanup the code by making a separate function for our data call
    fun requestData() {
        // Instantiate the cache
        val cache = DiskBasedCache(cacheDir, 1024 * 1024) // 1MB cap

        // Set up the network to use HttpURLConnection as the HTTP client.
        val network = BasicNetwork(HurlStack())

        // Instantiate the RequestQueue with the cache and network. Start the queue.
        val requestQueue = RequestQueue(cache, network).apply {
            start()
        }

        val url = "https://api.weather.gov/gridpoints/TOP/34,79/forecast/hourly"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                // Extract the list of hourly
                val hourlyList = response.getJSONObject("properties").getJSONArray("periods")

                // Log.e( "Debugging scroll list","Made the call!")
            },
            Response.ErrorListener { error ->
                // TODO: Handle error
                // Tbh for our purposes, just log it out so we know
                // In the ideal world, we'd do something like try again or some other fail safe
            }
        )

        requestQueue.add(jsonObjectRequest)
    }

    // We'll use this companion object in order to store key names
    // It's nice convention to do this, as this makes naming more evident
    // and easier to reuse in case we multiple things try to open this Activity
    companion object {
        val PARAM_CITY_NAME = "PARAM_CITY_NAME"
    }
}
