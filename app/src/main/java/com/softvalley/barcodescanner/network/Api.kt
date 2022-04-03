package com.softvalley.barcodescanner.network

import com.example.khataapp.models.LoginResponse
import com.softvalley.barcodescanner.dataModel.ProductResponse
import com.softvalley.barcodescanner.utils.CONSTANTS.Companion.API_ACCOUNT_LOGIN
import com.softvalley.barcodescanner.utils.CONSTANTS.Companion.API_PRODUCTS_PRODUCT_BY_UAN
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    /****************************   Authentication **********************/

    @GET(API_ACCOUNT_LOGIN)
    suspend fun login(
        @Query("username") username: String,
        @Query("password") password: String
    ):LoginResponse

    /****************************   Product **********************/

    @GET(API_PRODUCTS_PRODUCT_BY_UAN)
    suspend fun getProduct(@Query("UAN") uan:String):ProductResponse


}