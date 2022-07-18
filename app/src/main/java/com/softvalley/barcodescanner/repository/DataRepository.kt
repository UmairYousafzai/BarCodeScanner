package com.softvalley.barcodescanner.repository

import com.softvalley.barcodescanner.dataModel.authentication.LoginResponse
import com.softvalley.barcodescanner.dataModel.ProductResponse
import com.softvalley.barcodescanner.network.ResultWrapper
import com.softvalley.barcodescanner.network.RetrofitClient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DataRepository(){

    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

    suspend fun getProduct(uan:String): ResultWrapper<ProductResponse> {
        return safeApiCall(dispatcher) { RetrofitClient.getApi().getProduct(uan) }
    }


 suspend fun login(userName:String,password:String): ResultWrapper<LoginResponse> {
        return safeApiCall(dispatcher) { RetrofitClient.getApi().login(userName,password) }
    }

}