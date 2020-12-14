package com.example.mapdownloadapp

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.view.isVisible


import com.example.xmlparseapplication.data.Country
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_country.*


class MyCountryRecyclerViewAdapter(
    private val values: List<Country>,
    private val onItemClicked: (position: Int) -> Unit
) : RecyclerView.Adapter<MyCountryRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_country, parent, false)
        return ViewHolder(view,onItemClicked)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = values[position]
        var countryName: String
        if (item.translate != "") {
            if (item.translate.contains("name:en=")) {
                countryName = item.translate.replace("name:en=","")
                countryName = countryName.split(";")[0]
            }
            else{
                countryName = item.translate.split(";")[0]
            }

        }
        else{
            countryName = item.name.substring(0, 1).toUpperCase()+item.name.substring(1 )
        }


        holder.bind(item,countryName)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(override val containerView: View,
                           val onItemClicked: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        init{
            containerView.setOnClickListener{
                onItemClicked(adapterPosition)
            }
        }

        fun bind(item : Country,countryName : String){
            country_name.text = countryName
            download_button.isVisible = item.regionList.isEmpty()
        }

    }
}