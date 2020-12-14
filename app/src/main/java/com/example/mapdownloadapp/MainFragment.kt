package com.example.mapdownloadapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.xmlparseapplication.data.Continent
import com.example.xmlparseapplication.data.Country
import com.example.xmlparseapplication.data.District
import com.example.xmlparseapplication.data.Region
import kotlinx.android.synthetic.main.fragment_main.*
import org.xmlpull.v1.XmlPullParser
import java.text.FieldPosition

class MainFragment : Fragment(R.layout.fragment_main) {

    private var continent : Continent = Continent("","","","","","",
        mutableListOf<Country>()
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            val parser: XmlPullParser = resources.getXml(R.xml.regions)
            while (parser.eventType != XmlPullParser.END_DOCUMENT) {

                when (parser.eventType) {
                    XmlPullParser.START_TAG -> {

                        when (parser.depth) {
                            2 -> {
                                parseContinent(parser)
                            }
                            3 -> {
                                parseCountry(parser)
                            }
                            4 -> {
                                parseRegion(parser)
                            }
                        }
                    }
                    else -> {
                    }
                }
                parser.next()
            }
        } catch (t: Throwable) {
            Toast.makeText(requireContext(),
                "Ошибка при загрузке XML-документа: $t",
                Toast.LENGTH_LONG).show()
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        continent_name.text = continent.name.substring(0, 1).toUpperCase()+continent.name.substring(1 )
        with(country_list) {
            layoutManager = LinearLayoutManager(context)
            adapter = MyCountryRecyclerViewAdapter(continent.countryList) { position ->
                onCountryClick(position)}
        }

        memory_size_text.text = "Free " + android.text.format.Formatter.formatShortFileSize(requireContext(), requireContext().filesDir.usableSpace )
    }


    private fun onCountryClick(position: Int){
        if (continent.countryList[position].regionList.isNotEmpty()) {
            val action = MainFragmentDirections.actionMainFragmentToRegionsFragment(continent.countryList[position].regionList.toTypedArray())
            findNavController().navigate(action)
        }
    }

    private fun parseRegion(parser: XmlPullParser) {
        var type = ""
        var name = ""
        var translate = ""
        var srtm = ""
        var wiki = ""
        var hillshade = ""

        var i = 0
        while (i < parser.attributeCount) {
            when (parser.getAttributeName(i)) {
                "type" -> type = parser.getAttributeValue(i)
                "name" -> name = parser.getAttributeValue(i)
                "translate" -> translate = parser.getAttributeValue(i)
                "srtm" -> srtm = parser.getAttributeValue(i)
                "wiki" -> wiki = parser.getAttributeValue(i)
                "hillshade" -> hillshade = parser.getAttributeValue(i)
            }
            i++
        }
        continent.countryList.last().regionList = continent.countryList.last().regionList + Region(
            type, name, translate, srtm, wiki, hillshade
        )
    }

    private fun parseCountry(parser: XmlPullParser) {
        var name = ""
        var lang = ""
        var poly_extract = ""
        var inner_download_prefix = ""
        var join_map_files = ""
        var translate = ""
        var i = 0
        while (i < parser.attributeCount) {
            when (parser.getAttributeName(i)) {
                "name" -> name = parser.getAttributeValue(i)
                "lang" -> lang = parser.getAttributeValue(i)
                "poly_extract" -> poly_extract = parser.getAttributeValue(i)
                "inner_download_prefix" -> inner_download_prefix = parser.getAttributeValue(i)
                "join_map_files" -> join_map_files = parser.getAttributeValue(i)
                "translate" -> translate = parser.getAttributeValue(i)
            }
            i++
        }
        continent.countryList = continent.countryList + Country(
            name, lang, poly_extract, inner_download_prefix, join_map_files, translate,
            mutableListOf<Region>()
        )
    }

    private fun parseContinent(parser: XmlPullParser) {
        var i = 0
        while (i < parser.attributeCount) {
            when (parser.getAttributeName(i)) {
                "type" -> continent.type = parser.getAttributeValue(i)
                "boundary" -> continent.boundary = parser.getAttributeValue(i)
                "inner_download_suffix" -> continent.inner_download_suffix =
                    parser.getAttributeValue(i)
                "poly_extract" -> continent.poly_extract = parser.getAttributeValue(i)
                "name" -> continent.name = parser.getAttributeValue(i)
                "translate" -> continent.translate = parser.getAttributeValue(i)
            }
            i++
        }
    }
}