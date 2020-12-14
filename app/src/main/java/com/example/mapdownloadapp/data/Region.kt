package com.example.xmlparseapplication.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Region (
    var type: String,
    var name: String,
    var translate: String,
    var srtm: String,
    var wiki: String,
    var hillshade: String

) : Parcelable