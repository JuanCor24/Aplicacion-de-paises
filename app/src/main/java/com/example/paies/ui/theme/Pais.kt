package com.example.paies.ui.theme

import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.paies.R
import android.os.Bundle
import com.example.paies.Country


class Pais : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pais)

        // Obtener los datos del intent
        val country = intent.getParcelableExtra<Country>("country")

        // Comprobar si se recibieron datos del país correctamente
        if (country != null) {
            // Asignar los atributos del país a las vistas correspondientes
            val imageView: ImageView = findViewById(R.id.imagen2)
            val nombreTextView: TextView = findViewById(R.id.item_title11)
            val nombreNativoTextView: TextView = findViewById(R.id.item_title21)
            val monedaTextView: TextView = findViewById(R.id.item_title31)
            val siglasTextView: TextView = findViewById(R.id.item_title41)
            val alpha2CodeTextView: TextView = findViewById(R.id.item_title42)
            val alpha3CodeTextView: TextView = findViewById(R.id.item_title43)
            val regionTextView: TextView = findViewById(R.id.item_title44)
            val subRegionTextView: TextView = findViewById(R.id.item_title54)

            Glide.with(this)
                .load(country.flagPng)
                .into(imageView)
            nombreTextView.text = country.name
            nombreNativoTextView.text = country.nativeName
            monedaTextView.text = country.currencyName
            siglasTextView.text = country.currencySymbol
            alpha2CodeTextView.text = country.alpha2Code
            alpha3CodeTextView.text = country.alpha3Code
            regionTextView.text = country.region
            subRegionTextView.text = country.subRegion
        } else {
            // Manejar el caso en que no se recibieron datos correctamente

            finish()
        }
    }
}

