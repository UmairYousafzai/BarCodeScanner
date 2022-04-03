package com.softvalley.barcodescanner.ui

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.softvalley.barcodescanner.BuildConfig
import com.softvalley.barcodescanner.MainActivity
import com.softvalley.barcodescanner.databinding.FragmentSplashBinding
import com.softvalley.barcodescanner.utils.checkDate
import com.softvalley.barcodescanner.utils.hideToolbar
import com.softvalley.barcodescanner.utils.showToolbar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    override fun initViews() {

        binding.tvVersionName.text="Version ${BuildConfig.VERSION_NAME}"
        hideToolbar()
        startSplash()

    }

    private fun startSplash() {

        lifecycleScope.launch {
            dataStore.timeStamp.collect {
                Log.e("splash fragment ",it.toString())

                checkDate(it){ check->
                    if (check)
                    {
                        Handler(Looper.getMainLooper()).postDelayed({
                            moveToNextScreen(SplashFragmentDirections.actionSplashFragmentToLoginFragment())

                        },1000)

                        Log.e("splash fragment ","true")
                    }
                    else
                    {
                        Log.e("splash fragment ","false")
                        Handler(Looper.getMainLooper()).postDelayed({
                            moveToNextScreen(SplashFragmentDirections.actionSplashFragmentToCameraFragment())

                        },1000)
                    }
                }

            }
        }


    }


    override fun liveDataObserver() {
    }
    override fun getFragmentBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    )= FragmentSplashBinding .inflate(layoutInflater,container,false)

    override fun setDefaultUi() {
    }


}