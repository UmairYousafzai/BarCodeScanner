package com.softvalley.barcodescanner.ui

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.softvalley.barcodescanner.BuildConfig
import com.softvalley.barcodescanner.databinding.FragmentSplashBinding
import com.softvalley.barcodescanner.utils.DataStoreHelper
import com.softvalley.barcodescanner.utils.checkDate
import com.softvalley.barcodescanner.utils.hideToolbar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class SplashFragment : BaseFragment<FragmentSplashBinding>() {
    private lateinit var dataStore: DataStoreHelper

    override fun initViews() {
        dataStore = DataStoreHelper(requireContext())

        binding.tvVersionName.text = "Version ${BuildConfig.VERSION_NAME}"
        hideToolbar()
        startSplash()

    }

    private fun startSplash() {

        lifecycleScope.launch {
            dataStore.isLogin.collect {

                if (it.not()) {
                    Handler(Looper.getMainLooper()).postDelayed({
                        moveToNextScreen(SplashFragmentDirections.actionSplashFragmentToLoginFragment())

                    }, 1000)

                    Log.e("splash fragment ", "true")
                } else {
                    Log.e("splash fragment ", "false")
                    Handler(Looper.getMainLooper()).postDelayed({
                        moveToNextScreen(SplashFragmentDirections.actionSplashFragmentToCameraFragment())

                    }, 1000)
                }

            }
        }


    }


    override fun liveDataObserver() {
    }

    override fun getFragmentBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSplashBinding.inflate(layoutInflater, container, false)

    override fun setDefaultUi() {
    }


}