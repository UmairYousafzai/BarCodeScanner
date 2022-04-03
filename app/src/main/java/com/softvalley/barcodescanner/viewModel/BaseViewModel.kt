package com.softvalley.barcodescanner.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.softvalley.barcodescanner.network.ResultWrapper
import com.softvalley.barcodescanner.repository.DataRepository

open class BaseViewModel :ViewModel(){

     val dialogMessage = MutableLiveData<String>()
    val progressBar = MutableLiveData<Boolean>()
    val toastMessage=MutableLiveData<String>()
    val repository= DataRepository()

    protected fun showDialogMessage(message: String) {
        dialogMessage.value = message
    }

    protected fun showToastMessage(message: String) {
        toastMessage.value = message
    }


    protected fun showProgressBar(show: Boolean) {
        progressBar.value = show
    }

    protected fun handleErrorType(error: ResultWrapper<Any>) {
        when (error) {
            is ResultWrapper.NetworkError ->
                showDialogMessage("Internet not available")

            is ResultWrapper.GenericError ->
                showDialogMessage("" + error.error?.message)
        }
    }
}