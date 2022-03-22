package com.softvalley.barcodescanner.dataModel

import java.io.Serializable

data class ProductResponse(
    val Code: Int,
    val Data: Product?,
    val Message: String
):Serializable