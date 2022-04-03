package com.example.khataapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.softvalley.barcodescanner.dataModel.general.BaseResponse
import com.softvalley.barcodescanner.dataModel.general.User
import java.io.Serializable

data class LoginResponse(
    val Data:User
):Serializable,BaseResponse()



