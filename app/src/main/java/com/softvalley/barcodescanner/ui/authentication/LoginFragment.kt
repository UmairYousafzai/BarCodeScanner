package com.softvalley.barcodescanner.ui.authentication


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.softvalley.barcodescanner.databinding.FragmentLoginBinding
import com.softvalley.barcodescanner.ui.BaseFragment
import com.softvalley.barcodescanner.utils.getDate
import com.softvalley.barcodescanner.utils.observeText
import com.softvalley.barcodescanner.utils.showToolbar
import com.softvalley.barcodescanner.viewModel.LoginViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class LoginFragment :BaseFragment<FragmentLoginBinding> (){
    private  val  viewModel: LoginViewModel by viewModels()

    override fun initViews() {
        binding.viewModel=viewModel
        textObservers()


    }


    override fun liveDataObserver() {
        with(viewModel){
            setupGeneralViewModel(this)
            userNameErrorMutableLiveData.observe(viewLifecycleOwner){ error->
                binding.etUsernameLayout.error= error
            }

            passwordErrorMutableLiveData.observe(viewLifecycleOwner){ error->
                binding.etPasswordLayout.error= error
            }

            loginResponseMutableLiveData.observe(viewLifecycleOwner){
                saveDataToDataStore()

                moveToNextScreen(LoginFragmentDirections.actionLoginFragmentToCameraFragment())
            }
        }
    }

    private fun saveDataToDataStore() {
        lifecycleScope.launch (Dispatchers.IO){
            dataStore.saveLoginTimeStamp(getDate())

        }
    }

    private fun textObservers() {
        binding.etUsername.observeText {
            if (!it.isNullOrBlank())
                binding.etUsernameLayout.error=null
        }
        binding.etPassword.observeText {
            if (!it.isNullOrBlank())
                binding.etPasswordLayout.error=null
        }

    }
    override fun getFragmentBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    )= FragmentLoginBinding.inflate(layoutInflater,container,false)

    override fun setDefaultUi() {
    }

}