package com.softvalley.barcodescanner.ui

import android.util.Log
import com.budiyev.android.codescanner.*
import com.softvalley.barcodescanner.R
import com.softvalley.barcodescanner.databinding.FragmentCameraBinding
import com.softvalley.barcodescanner.utils.checkCameraPermission
import com.softvalley.barcodescanner.utils.requestPermission
import com.softvalley.barcodescanner.utils.showToast
import com.softvalley.barcodescanner.viewModel.BaseViewModel
import com.softvalley.barcodescanner.viewModel.CameraViewModel


class CameraFragment : BaseFragment<FragmentCameraBinding, CameraViewModel>(){
    private var codeScanner: CodeScanner?= null
    private val TAG=CameraFragment::class.simpleName





    override fun initViews() {
        codeScanner()

    }



    private fun liveDataObservers() {


        with(viewModel){
            setupGeneralViewModel(this)
            productLiveData.observe(viewLifecycleOwner) { product->
                binding.product=product
                codeScanner?.startPreview()
            }

            dialogMessage.observe(viewLifecycleOwner) {
                showToast(it)
                codeScanner?.startPreview()
            }
        }
    }

    private fun btnListeners() {

        binding.codeScanner.setOnClickListener {
            codeScanner?.startPreview()
        }

    }

    override fun onResume() {
        super.onResume()

        if (checkCameraPermission())
        {

            codeScanner?.startPreview()
            btnListeners()
            liveDataObservers()
        }
        else
        {
            requestPermission()

        }
    }

    override fun onPause() {
        codeScanner?.releaseResources()
        super.onPause()

    }


    private fun codeScannerCallBack() {

       with(codeScanner){
           this?.decodeCallback = DecodeCallback{
               requireActivity().runOnUiThread{

                   viewModel.getProduct(it.text)
                   Log.e(TAG,it.text)
               }

       }

           this?.errorCallback = ErrorCallback {
               requireActivity().runOnUiThread {
                   it.message?.let { it1 ->
                       showToast(it1)
                       Log.e(TAG,it.message.toString())

                   }
               }
           }
        }

    }

    private fun codeScanner() {

        codeScanner= CodeScanner(requireContext(),binding.codeScanner)

        codeScanner?.apply {
            camera=CodeScanner.CAMERA_BACK
            formats= CodeScanner.ALL_FORMATS
            autoFocusMode= AutoFocusMode.CONTINUOUS
            scanMode= ScanMode.SINGLE
            isAutoFocusEnabled=true
            isFlashEnabled=false
        }




        codeScannerCallBack()

    }



    override fun getViewModelClass()= CameraViewModel::class.java

    override fun getFragmentView()= R.layout.fragment_camera
    override fun setDefaultUi() {
    }




}