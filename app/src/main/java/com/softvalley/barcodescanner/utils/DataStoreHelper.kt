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


    val isLogin:Flow<Boolean> = dataStore.data.map { preferences->
        preferences[IS_LOGIN]?:false
    }

    val iP:Flow<String> = dataStore.data.map { preferences->
        preferences[IP]?:"192.168.10.1"
    }


    suspend fun saveIp(ip:String){
        dataStore.edit { preferences->
            preferences[IP]=ip
        }
    }
    suspend fun setIsLogin(flag:Boolean){
        dataStore.edit { preferences->
            preferences[IS_LOGIN]=flag
        }
    }



    suspend fun clear(){
        dataStore.edit {
            it.clear()


        }
    }

    companion object {
        const val DATA_STORE_NAME="barcode_scanner_datastore"
        val IS_LOGIN= preferencesKey<Boolean>(name = "is login")
        val IP= preferencesKey<String>(name = "ip")
    }

}


