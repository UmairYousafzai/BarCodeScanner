package com.softvalley.barcodescanner.dataModel.general

import java.io.Serializable

open class BaseResponse(
    val Code: Int=0,
    val Message: String=""
):Serializable