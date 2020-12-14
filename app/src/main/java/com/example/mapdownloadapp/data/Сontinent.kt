package com.example.xmlparseapplication.data

data class Continent(
    var type: String,
    var name: String,
    var translate: String,
    var inner_download_suffix: String,
    var boundary: String,
    var poly_extract: String,
    var countryList: List<Country>
)