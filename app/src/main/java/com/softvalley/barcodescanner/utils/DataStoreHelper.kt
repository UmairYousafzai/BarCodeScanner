package com.softvalley.barcodescanner.utils

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreHelper(
    context: Context
) {
    private val applicationContext: Context = context.applicationContext

    private val dataStore:DataStore<Preferences> = applicationContext.createDataStore(name = DATA_STORE_NAME)

    val timeStamp:Flow<String> = dataStore.data.map { preferences->
        preferences[LOGIN_TIME_STAMP]?:""
    }


    suspend fun saveLoginTimeStamp(timeStamp:String){
        dataStore.edit { preferences->
            preferences[LOGIN_TIME_STAMP]=timeStamp
        }
    }

    suspend fun clear(){
        dataStore.edit {
            it.clear()


        }
    }

    companion object {
        const val DATA_STORE_NAME="barcode_scanner_datastore"
        val LOGIN_TIME_STAMP= preferencesKey<String>(name = "Login_Time_Stamp")
    }

}


