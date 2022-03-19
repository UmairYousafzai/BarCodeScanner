package com.softvalley.barcodescanner.ui


import android.util.Log
import android.view.View
import android.widget.Toast
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.softvalley.barcodescanner.R
import com.softvalley.barcodescanner.databinding.FragmentCameraBinding
import com.softvalley.barcodescanner.utils.checkPermission
import com.softvalley.barcodescanner.utils.showToast

class CameraFragment() : BaseFragment(R.layout.fragment_camera) {

    private lateinit var binding:FragmentCameraBinding
    lateinit var codeScanner: CodeScanner

    override fun initViews(view: View) {

        binding = FragmentCameraBinding.inflate(layoutInflater)

        checkPermission()
        codeScanner()
        binding.codeScanner.setOnClickListener {
            codeScanner.startPreview()
        }
    }

    private fun codeScannerCallBack() {

        codeScanner.decodeCallback= DecodeCallback{
            requireActivity().runOnUiThread{

                Toast.makeText(requireContext(),it.text,Toast.LENGTH_SHORT).show()
                Log.e(CameraFragment::class.simpleName,it.text)
            }

        }

        codeScanner.errorCallback = ErrorCallback {
            requireActivity().runOnUiThread {
                Toast.makeText(requireContext(), "Camera initialization error: ${it.message}",
                    Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun codeScanner() {

        codeScanner= CodeScanner(requireContext(),binding.codeScanner)

        codeScanner.camera=CodeScanner.CAMERA_BACK
        codeScanner.formats= CodeScanner.ALL_FORMATS
        codeScanner.autoFocusMode= AutoFocusMode.CONTINUOUS
        codeScanner.scanMode= ScanMode.CONTINUOUS
        codeScanner.isAutoFocusEnabled=true
        codeScanner.isFlashEnabled=true



//        codeScanner.startPreview()
        codeScannerCallBack()

    }

    override fun attachViewModel() {
    }

    override fun setDefaultUi() {
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()


    }






}