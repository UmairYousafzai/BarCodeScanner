package com.softvalley.barcodescanner.dataModel

import java.io.Serializable

data class Product(
    val Description: String,
    val Uan: String,
    val UnitRetail: Double
):Serializable