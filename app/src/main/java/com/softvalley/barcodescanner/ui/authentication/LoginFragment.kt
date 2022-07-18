package com.softvalley.barcodescanner.ui.authentication


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.softvalley.barcodescanner.databinding.FragmentLoginBinding
import com.softvalley.barcodescanner.ui.BaseFragment
import com.softvalley.barcodescanner.utils.DataStoreHelper
import com.softvalley.barcodescanner.utils.getDate
import com.softvalley.barcodescanner.utils.observeText
import com.softvalley.barcodescanner.viewModel.LoginViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var dataStore: DataStoreHelper

    override fun initViews() {
        binding.viewModel = viewModel
        dataStore = DataStoreHelper(requireContext())
        textObservers()
    }





    override fun liveDataObserver() {
        with(viewModel) {
            setupGeneralViewModel(this)
            userNameErrorMutableLiveData.observe(viewLifecycleOwner) { error ->
                binding.etUsernameLayout.error = error
            }

            passwordErrorMutableLiveData.observe(viewLifecycleOwner) { error ->
                binding.etPasswordLayout.error = error
            }

            loginResponseMutableLiveData.observe(viewLifecycleOwner) {
                saveDataToDataStore()

                moveToNextScreen(LoginFragmentDirections.actionLoginFragmentToCameraFragment())
            }


        }

    }


    private fun saveDataToDataStore() {
        lifecycleScope.launch(Dispatchers.IO) {
            dataStore.setIsLogin(true)

        }
    }

    private fun textObservers() {
        binding.etUsername.observeText {
            if (it.isNotBlank())
                binding.etUsernameLayout.error = null
        }
        binding.etPassword.observeText {
            if (it.isNotBlank())
                binding.etPasswordLayout.error = null
        }

    }

    override fun getFragmentBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(layoutInflater, container, false)

    override fun setDefaultUi() {
    }

}