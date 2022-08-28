package com.softvalley.barcodescanner.ui

import android.media.MediaPlayer
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.budiyev.android.codescanner.*
import com.softvalley.barcodescanner.R
import com.softvalley.barcodescanner.databinding.FragmentCameraBinding
import com.softvalley.barcodescanner.utils.*
import com.softvalley.barcodescanner.viewModel.CameraViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class CameraFragment : BaseFragment<FragmentCameraBinding>(){
    lateinit var codeScanner: CodeScanner
    private val TAG=CameraFragment::class.simpleName
    private val viewModel:CameraViewModel by viewModels()
    private lateinit var dataStore: DataStoreHelper


    override fun initViews() {
        dataStore = DataStoreHelper(requireContext())
        showToolbar()
        checkPermission()
        codeScanner()
        btnListeners()

    }



    override fun liveDataObserver() {

        with(viewModel){
            setupGeneralViewModel(this)
            productLiveData.observe(viewLifecycleOwner) { product->
                binding.product=product
                val mp= MediaPlayer.create(requireContext(),R.raw.success_sound_effect)
                mp.start()
                mp.setOnCompletionListener {
                    mp.release()
                }

            }
            errorLiveData.observe(viewLifecycleOwner){
                val mp= MediaPlayer.create(requireContext(),R.raw.not_found_female)
                mp.start()
                mp.setOnCompletionListener {
                    mp.release()
                }
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




    override fun setDefaultUi() {
    }

    override fun getFragmentBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?): FragmentCameraBinding = FragmentCameraBinding.inflate(layoutInflater,container,false)

}