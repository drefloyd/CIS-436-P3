package com.example.cis_436_p3

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.cis_436_p3.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGetCatData.setOnClickListener{
            printCatData()
        }
    }

    fun printCatData(){
        var catURL = "https://api.thecatapi.com/v1/breeds" +
                "?api_key=live_dQlJg5Hpf9ynJf99MgbIurFObl3wJkQ5u6DN2fBq0UbdprcAEVVhXlAU7r9yX09d"

        val queue = Volley.newRequestQueue(this)

        val stringRequest = StringRequest(
            Request.Method.GET, catURL,
            { response ->
                var catsArray : JSONArray = JSONArray(response)

                for (i in 0 until catsArray.length()){  // index for cat array

                    // gets a specific cat
                    var theCat : JSONObject = catsArray.getJSONObject(i)

                    // gets name and description for each cat
                    Log.i("MainActivity", "Cat Name: ${theCat.getString("name")}")
                    Log.i("MainActivity", "Cat Description: " +
                            "${theCat.getString("description")}")
                }
                Log.i("MainActivity", response.toString())
            },
            {
                Log.i("MainActivity", "That did not work.") })

        queue.add(stringRequest)
    }
}