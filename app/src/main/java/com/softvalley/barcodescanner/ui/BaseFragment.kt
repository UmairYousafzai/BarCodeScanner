package com.softvalley.barcodescanner.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment (var layoutResID:Int):Fragment(layoutResID) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    abstract fun initViews(view: View)

    abstract fun attachViewModel()

    abstract fun setDefaultUi()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        getNetworkLiveData()
        initViews(view)
        attachViewModel()
        setDefaultUi()
    }
}