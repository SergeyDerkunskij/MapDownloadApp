package com.example.xmlparseapplication.data

data class Country(
    val name: String,
    val lang: String,
    val poly_extract: String,
    val inner_download_prefix: String,
    val join_map_files: String,
    val translate: String,
    var regionList: List<Region>
)