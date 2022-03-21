package com.softvalley.barcodescanner.ui

import android.util.Log
import android.widget.Toast
import com.budiyev.android.codescanner.*
import com.softvalley.barcodescanner.R
import com.softvalley.barcodescanner.databinding.FragmentCameraBinding
import com.softvalley.barcodescanner.utils.checkPermission
import com.softvalley.barcodescanner.utils.showToast
import com.softvalley.barcodescanner.viewModel.CameraViewModel


class CameraFragment : BaseFragment<FragmentCameraBinding, CameraViewModel>(){
    lateinit var codeScanner: CodeScanner
    private val TAG=CameraFragment::class.simpleName



    override fun initViews() {


        checkPermission()
        codeScanner()

        btnListeners()
        liveDataObservers()
    }

    private fun liveDataObservers() {


        with(viewModel){
            setupGeneralViewModel(this)
            productLiveData.observe(viewLifecycleOwner) { product->
                binding.product=product
            }
        }
    }

    private fun btnListeners() {

        binding.codeScanner.setOnClickListener {
            codeScanner.startPreview()
        }

    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()

    }


    private fun codeScannerCallBack() {

        codeScanner.decodeCallback= DecodeCallback{
            requireActivity().runOnUiThread{

                viewModel.getProduct(it.text)
                showToast(it.text)
                Log.e(TAG,it.text)
            }

        }

        codeScanner.errorCallback = ErrorCallback {
            requireActivity().runOnUiThread {
                it.message?.let { it1 -> showToast(it1) }
            }
        }
    }

    private fun codeScanner() {

        codeScanner= CodeScanner(requireContext(),binding.codeScanner)

        codeScanner.camera=CodeScanner.CAMERA_BACK
        codeScanner.formats= CodeScanner.ALL_FORMATS
        codeScanner.autoFocusMode= AutoFocusMode.CONTINUOUS
        codeScanner.scanMode= ScanMode.SINGLE
        codeScanner.isAutoFocusEnabled=true
        codeScanner.isFlashEnabled=false



        codeScanner.startPreview()
        codeScannerCallBack()

    }



    override fun getViewModelClass()= CameraViewModel::class.java

    override fun getFragmentView()= R.layout.fragment_camera
    override fun setDefaultUi() {
    }


}