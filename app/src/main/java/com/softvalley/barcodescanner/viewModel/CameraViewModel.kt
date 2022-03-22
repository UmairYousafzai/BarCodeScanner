package com.softvalley.barcodescanner.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softvalley.barcodescanner.dataModel.Product
import com.softvalley.barcodescanner.dataModel.ProductResponse
import com.softvalley.barcodescanner.network.ResultWrapper
import kotlinx.coroutines.launch

class CameraViewModel : BaseViewModel() {

    val productLiveData: MutableLiveData<Product> = MutableLiveData()


    fun getProduct(uan: String) {
        showProgressBar(true)
        viewModelScope.launch {
            repository.getProduct(uan).let { response ->
                showProgressBar(false)
                when (response) {
                    is ResultWrapper.Success ->
                        if (response.value.Code == 200) {
                            if (response.value.Data?.Description?.isNotEmpty() == true)
                            {
                                productLiveData.value = response.value.Data
                            }
                            else{
                                showDialogMessage("Product Not Found")

                            }
                        }
                    else -> handleErrorType(response)
                }
            }
        }
    }


}


