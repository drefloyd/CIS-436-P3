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


class MainActivity : AppCompatActivity(), FirstFragment.OnBreedSelectedListener {


    private lateinit var binding: ActivityMainBinding

    private val catBreedsList = mutableListOf<String>()

    private val catTemperamentList = mutableListOf<String>()

    private val catOriginList = mutableListOf<String>()


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

                    val catName = theCat.getString("name")
                    catBreedsList.add(catName)

                    val catTemperament = theCat.getString("temperament")
                    catTemperamentList.add(catTemperament)

                    val catOrigin = theCat.getString("origin")
                    catOriginList.add(catOrigin)

                    // gets name and description for each cat
                    Log.i("MainActivity", "Cat Name: ${theCat.getString("name")}")
                    Log.i("MainActivity", "Cat Description: " +
                            "${theCat.getString("description")}")
                }
                Log.i("MainActivity", response.toString())
                val firstFragment =
                    supportFragmentManager.findFragmentById(R.id.FirstFragmentContainerView) as FirstFragment
                firstFragment.populateDropDown(catBreedsList)
            },
            {
                Log.i("MainActivity", "That did not work.") })


        queue.add(stringRequest)
    }

    override fun onBreedSelected(selectedBreed: String) {
        val frag2 = supportFragmentManager.findFragmentById(R.id.SecondFragmentContainerView) as SecondFragment

        Log.i("MainActivity", "Selected breed: $selectedBreed")

        val index = catBreedsList.indexOf(selectedBreed)

        val temperament = catTemperamentList[index]

        val origin = catOriginList[index]

        frag2.recieveData(selectedBreed,temperament, origin)
    }
}
