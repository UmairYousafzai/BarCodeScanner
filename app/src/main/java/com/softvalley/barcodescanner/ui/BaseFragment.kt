package com.softvalley.barcodescanner.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.softvalley.barcodescanner.utils.DialogUtils
import com.softvalley.barcodescanner.utils.showAlertDialog
import com.softvalley.barcodescanner.utils.showToast
import com.softvalley.barcodescanner.viewModel.BaseViewModel

abstract class BaseFragment <T: ViewBinding,VM:ViewModel>:Fragment() {

    protected lateinit var binding:T
    protected lateinit var viewModel:VM
    lateinit var dialog: AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dialog = DialogUtils.getProgressDialog(requireContext())

        binding =DataBindingUtil.inflate(inflater,getFragmentView(),container,false)
        attachViewModel()
       return binding.root
    }


    abstract fun initViews()

    abstract fun getViewModelClass(): Class<VM>

    abstract fun getFragmentView(): Int




    abstract fun setDefaultUi()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        attachViewModel()
        setDefaultUi()
    }

    private fun attachViewModel()
    {
        viewModel = ViewModelProvider(this).get(getViewModelClass())

    }

    protected fun setupGeneralViewModel(generalViewModel: BaseViewModel) {
        with(generalViewModel) {
            dialogMessage.observe(viewLifecycleOwner) {
//               showAlertDialog(it)
                showToast(it)
            }

            progressBar.observe(viewLifecycleOwner) {
                    showProgressDialog(it)

            }
        }
    }


    protected fun showProgressDialog(show: Boolean) {

        if (show) {
            if (!dialog.isShowing)
                dialog.apply { show() }
        } else if (!show) {
            if (dialog.isShowing)
                dialog.dismiss()
        }
    }
}