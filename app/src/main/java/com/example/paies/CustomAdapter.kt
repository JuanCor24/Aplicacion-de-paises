package com.example.paies

import android.content.Intent
import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.Serializable
import java.net.URL



class CustomAdapter(private val countries: List<Country>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    var onItemClick: ((Country) -> Unit)? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = countries[position]
        holder.itemNativoPais.text = country.nativeName
        holder.itemPais.text = country.name
        holder.moneda.text = country.currencyName
        holder.siglas.text = country.currencySymbol
        Glide.with(holder.itemView)
            .load(country.flagPng)
            .into(holder.itemImage)
        holder.itemImage.setOnClickListener(){
            onItemClick?.invoke(country)
        }
        holder.button.setOnClickListener {
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:${country.numericCode}")
            holder.itemView.context.startActivity(dialIntent)
        }
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView = itemView.findViewById(R.id.imagen)
        var itemPais: TextView = itemView.findViewById(R.id.item_title)
        var itemNativoPais: TextView = itemView.findViewById(R.id.item_title2)
        var moneda: TextView = itemView.findViewById(R.id.item_title3)
        var siglas: TextView = itemView.findViewById(R.id.item_title4)
        var button: Button = itemView.findViewById(R.id.button)
    }
}
data class Country(
    val name: String,
    val nativeName: String,
    val currencyName: String,
    val currencySymbol: String,
    val flagPng: String,
    val region: String,
    val subRegion: String,
    val alpha2Code: String,
    val alpha3Code: String,
    val numericCode: String,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(nativeName)
        parcel.writeString(currencyName)
        parcel.writeString(currencySymbol)
        parcel.writeString(flagPng)
        parcel.writeString(region)
        parcel.writeString(subRegion)
        parcel.writeString(alpha2Code)
        parcel.writeString(alpha3Code)
        parcel.writeString(numericCode)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Country> {
        override fun createFromParcel(parcel: Parcel): Country {
            return Country(parcel)
        }

        override fun newArray(size: Int): Array<Country?> {
            return arrayOfNulls(size)
        }
    }
}