package com.softvalley.barcodescanner.repository

import com.example.khataapp.models.LoginResponse
import com.softvalley.barcodescanner.dataModel.ProductResponse
import com.softvalley.barcodescanner.network.ResultWrapper
import com.softvalley.barcodescanner.network.RetrofitClient
import com.softvalley.barcodescanner.network.RetrofitClient2
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DataRepository(){

    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

    suspend fun getProduct(uan:String): ResultWrapper<ProductResponse> {
        return safeApiCall(dispatcher) { RetrofitClient.getApi().getProduct(uan) }
    }


 suspend fun login(userName:String,password:String): ResultWrapper<LoginResponse> {
        return safeApiCall(dispatcher) { RetrofitClient2.getApi().login(userName,password) }
    }

}