package com.example.mapdownloadapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xmlparseapplication.data.Region
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_country.*

class MyRegionRecyclerViewAdapter(
    private val values: Array<Region>
) : RecyclerView.Adapter<MyRegionRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_country, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = values[position]
        var regionName: String
        if (item.translate != "") {
            if (item.translate.contains("name:en=")) {
                regionName = item.translate.replace("name:en=","")
                regionName = regionName.split(";")[0]
            }
            else{
                regionName = item.translate.split(";")[0]
            }

        }
        else{
            regionName = item.name.substring(0, 1).toUpperCase()+item.name.substring(1 )
        }
        holder.bind(regionName)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(regionName : String){
            country_name.text = regionName
        }

    }

}