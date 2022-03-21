package com.softvalley.barcodescanner.dataModel.utilModel

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ApiErrorResponse(
    @SerializedName("code")
    val code: Int = 0,
    @SerializedName("message")
    val message: String = ""
) : Serializable