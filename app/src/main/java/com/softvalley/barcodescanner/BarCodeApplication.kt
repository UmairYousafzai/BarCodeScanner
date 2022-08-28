package com.softvalley.barcodescanner

import android.app.Application
import android.util.Log
import com.softvalley.barcodescanner.utils.DataStoreHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class BarCodeApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        val dataStoreHelper= DataStoreHelper(applicationContext)

        GlobalScope.launch {
            dataStoreHelper.iP.collect {
                baseUrl=it
                Log.e("error","true")
            }
        }

    }


    companion object{
        var baseUrl=""
    }
}