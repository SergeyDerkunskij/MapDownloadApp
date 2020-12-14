package com.example.mapdownloadapp


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.country_list
import kotlinx.android.synthetic.main.fragment_regions.*

class RegionsFragment : Fragment(R.layout.fragment_regions) {

    private val args : RegionsFragmentArgs by navArgs()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        backButton.setOnClickListener{
            findNavController().navigate(R.id.action_regionsFragment_to_mainFragment)
        }
        with(country_list) {
            layoutManager = LinearLayoutManager(context)
            adapter = MyRegionRecyclerViewAdapter(args.regionList)
        }
    }

}