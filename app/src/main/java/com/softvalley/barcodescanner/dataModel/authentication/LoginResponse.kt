package com.softvalley.barcodescanner.dataModel.authentication;

import com.softvalley.barcodescanner.dataModel.general.BaseResponse
import com.softvalley.barcodescanner.dataModel.general.User
import java.io.Serializable

data class LoginResponse(
    val Data:User
):Serializable,BaseResponse()



