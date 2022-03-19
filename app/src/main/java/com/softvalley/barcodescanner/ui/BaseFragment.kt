package com.softvalley.barcodescanner.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment (layoutResID:Int):Fragment(layoutResID) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    abstract fun initViews(view: View)

    abstract fun attachViewModel()

    abstract fun setDefaultUi()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        attachViewModel()
        setDefaultUi()
    }
}