package com.example.paies


import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.paies.ui.theme.Pais

import org.json.JSONObject



class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        // Crear la lista de países


        var paises: JSONObject = loadJSONfromString(loadJSONfromAssets("data.json"))
        var lista: ArrayList<Country> = ArrayList()
        for (i in 0 until paises.getInt("TotalCount")) {
            val countryName = """${paises.getJSONArray("Countries").getJSONObject(i).getString("Name")}""".trimMargin()
            val nativeName = """${paises.getJSONArray("Countries").getJSONObject(i).getString("NativeName")}""".trimMargin()
            val currencyName = """${paises.getJSONArray("Countries").getJSONObject(i).getString("CurrencyName")}""".trimMargin()
            val currencySymbol = """${paises.getJSONArray("Countries").getJSONObject(i).getString("CurrencySymbol")}""".trimMargin()
            val flagUrl = """${paises.getJSONArray("Countries").getJSONObject(i).getString("FlagPng")}""".trimMargin()
            val region = """${paises.getJSONArray("Countries").getJSONObject(i).getString("Region")}""".trimMargin()
            val subRegion = """${paises.getJSONArray("Countries").getJSONObject(i).getString("SubRegion")}""".trimMargin()
            val alpha2Code = """${paises.getJSONArray("Countries").getJSONObject(i).getString("Alpha2Code")}""".trimMargin()
            val alpha3Code = """${paises.getJSONArray("Countries").getJSONObject(i).getString("Alpha3Code")}""".trimMargin()
            val numericCode = """${paises.getJSONArray("Countries").getJSONObject(i).getString("NumericCode")}""".trimMargin()
            var country = Country(
                countryName,
                nativeName,
                currencyName,
                currencySymbol,
                flagUrl,
                region,
                subRegion,
                alpha2Code,
                alpha3Code,
                numericCode
            )
            lista.add(country)
        }

        // Crear el adaptador y pasar la lista de países
        val adapter = CustomAdapter(lista)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        adapter.onItemClick = { country ->
            val intent = Intent(this, Pais::class.java)
            intent.putExtra("country", country)
            startActivity(intent)
        }

        }


    fun loadJSONfromAssets(name: String): String {
        return try {
            val inputStream = assets.open(name)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, charset("UTF-8"))
        } catch (ex: java.io.IOException) {
            ex.printStackTrace()
            ""
        }
    }

    fun loadJSONfromString(jsonString: String): JSONObject {
        return try {
            JSONObject(jsonString)
        } catch (ex: org.json.JSONException) {
            ex.printStackTrace()
            JSONObject()
        }
    }

}



