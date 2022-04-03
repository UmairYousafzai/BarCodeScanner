package com.softvalley.barcodescanner.dataModel

import com.softvalley.barcodescanner.dataModel.general.BaseResponse
import java.io.Serializable

data class ProductResponse(
    val Data: Product?,
):Serializable, BaseResponse()