package com.softvalley.barcodescanner.network

import com.softvalley.barcodescanner.dataModel.ProductResponse
import com.softvalley.barcodescanner.utils.CONSTANTS.Companion.API_PRODUCTS_PRODUCT_BY_UAN
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {


    @GET(API_PRODUCTS_PRODUCT_BY_UAN)
    suspend fun getProduct(@Query("UAN") uan:String):ProductResponse
}