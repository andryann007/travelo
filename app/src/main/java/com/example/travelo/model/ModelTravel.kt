package com.example.travelo.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ModelTravel(
    val id : Int? = 0,
    val date: String? = null,
    val name: String? = null,
    val location: String? = null,
    val image: String? = null,
    val description: String? = null,
    val price: Int? = 0,
    val open_time: String? = null
) : Parcelable