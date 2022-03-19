package com.softvalley.barcodescanner.utils

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.softvalley.barcodescanner.databinding.CustomAlertDialogBinding
import java.util.*

fun Fragment.showToast(message:String)
{
    Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
}

fun Fragment.checkPermission(boolean: (Boolean) -> Unit) {
    val permissions = ContextCompat.checkSelfPermission(requireContext(),android.Manifest.permission.CAMERA)
    val dialogBinding= CustomAlertDialogBinding.inflate(layoutInflater)
    val alertDialog: AlertDialog =
        AlertDialog.Builder(requireContext()).setView(dialogBinding.root).setCancelable(false).create()


    dialogBinding.btnYes.setOnClickListener {
        ActivityCompat.requestPermissions(requireActivity(),
            arrayOf(android.Manifest.permission.CAMERA), 1)
    }


    if(permissions!=PackageManager.PERMISSION_GRANTED)
    {
        if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            dialogBinding.title.text = "Camera Permission Needed";
            dialogBinding.body.text = "This Permission Needed For The Better Experience Of The App. "
            alertDialog.show();


        } else {
            ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(android.Manifest.permission.CAMERA), 1)

        }
    }
    else
    {
        showToast("Camera Permission needed for the app use")
    }


}

