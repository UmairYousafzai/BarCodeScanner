package com.softvalley.barcodescanner.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.softvalley.barcodescanner.dataModel.general.User
import com.softvalley.barcodescanner.network.ResultWrapper
import kotlinx.coroutines.launch

class LoginViewModel : BaseViewModel() {

    val userNameMutableLiveData = MutableLiveData<String>()
    val passwordMutableLiveData = MutableLiveData<String>()
    val loginResponseMutableLiveData = MutableLiveData<User>()
    val userNameErrorMutableLiveData = MutableLiveData<String>()
    val passwordErrorMutableLiveData = MutableLiveData<String>()

    fun onClick() {
        loginUser()
    }

    private fun loginUser() {
        val username: String? = userNameMutableLiveData.value
        val password: String? = passwordMutableLiveData.value
        if (!username.isNullOrBlank()) {
            if (!password.isNullOrBlank()) {
                viewModelScope.launch {
                    showProgressBar(true)
                    repository.login(username, password).let { response ->
                        showProgressBar(false)
                        when (response) {
                            is ResultWrapper.Success ->
                            {
                                if (response.value.Code == 200)
                                    loginResponseMutableLiveData.value = response.value.Data
                                else {
                                userNameErrorMutableLiveData.value = response.value.Message
                                passwordErrorMutableLiveData.value = response.value.Message
                            }
                                showToastMessage(response.value.Message)

                            }




                            else -> handleErrorType(response)
                        }
                    }
                }
            } else {
                passwordErrorMutableLiveData.value = "Please Enter Password"
            }

        } else {
            userNameErrorMutableLiveData.value = "Please Enter User Name"
        }

    }
}