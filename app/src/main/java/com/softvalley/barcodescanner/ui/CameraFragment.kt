package com.softvalley.barcodescanner.ui


import android.util.Log
import android.view.View
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ScanMode
import com.softvalley.barcodescanner.R
import com.softvalley.barcodescanner.databinding.FragmentCameraBinding
import com.softvalley.barcodescanner.utils.showToast

class CameraFragment() : BaseFragment(R.layout.fragment_camera) {

    lateinit var binding:FragmentCameraBinding
    lateinit var codeScanner: CodeScanner

    override fun initViews(view: View) {

        binding = FragmentCameraBinding.inflate(layoutInflater)

        codeScanner()
        codeScannerCallBack()
    }

    private fun codeScannerCallBack() {

        codeScanner.decodeCallback= DecodeCallback{

        }
    }

    private fun codeScanner() {

        codeScanner= CodeScanner(requireContext(),binding.codeScanner)

        codeScanner.camera=CodeScanner.CAMERA_BACK
        codeScanner.formats= CodeScanner.ALL_FORMATS
        codeScanner.autoFocusMode= AutoFocusMode.CONTINUOUS
        codeScanner.scanMode= ScanMode.CONTINUOUS
        codeScanner.isAutoFocusEnabled=true
        codeScanner.isFlashEnabled=false
        codeScanner.decodeCallback= DecodeCallback {
            requireActivity().runOnUiThread{
                Log.e(CameraFragment::class.simpleName, )
            }

        }

    }

    override fun attachViewModel() {
    }

    override fun setDefaultUi() {
    }




}