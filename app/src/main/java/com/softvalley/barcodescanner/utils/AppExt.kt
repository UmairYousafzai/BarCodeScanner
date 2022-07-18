package com.softvalley.barcodescanner.utils

import android.Manifest
import android.app.AlertDialog
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.softvalley.barcodescanner.R
import com.softvalley.barcodescanner.databinding.CustomAlertDialogBinding
import java.text.SimpleDateFormat
import java.util.*


fun getDate(): String {
    val calendar = Calendar.getInstance()

    val format = SimpleDateFormat("dd/MM/yyyy")
    return format.format(calendar.time)
}
fun checkDate(loginDate:String,dateCheckCallBack:(Boolean)->Unit)
{
    val sdf = SimpleDateFormat("dd/MM/yyyy")


    if (!loginDate.isNullOrEmpty())
    {
        var strLoginDate: Date = sdf.parse(loginDate)
        var strCurrentDate: Date = sdf.parse(getDate())
        Log.e("check date:",strCurrentDate.time.toString())
        if (strCurrentDate > strLoginDate)
        {
            dateCheckCallBack.invoke(true)

        }
        else
        {
            dateCheckCallBack.invoke(false)
        }
        Log.e("check date :",Date().after(strLoginDate).toString())

    }
    else
    {
        dateCheckCallBack.invoke(true)
        Log.e("check date :","else false")

    }
        Log.e("check date :","trying")




}

fun TextInputEditText.observeText(afterTextChanged:(String) -> Unit)
{
    this.addTextChangedListener(object :TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

    })
}
fun Fragment.showToast(message:String)
{
    Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
}

fun Fragment.hideToolbar()
{
    ( requireActivity() as AppCompatActivity).supportActionBar?.hide()
}

fun Fragment.showToolbar()
{
    ( requireActivity() as AppCompatActivity).supportActionBar?.show()
}
fun Fragment.showAlertDialog(msg: String) {
    var newMessage = msg
    if (newMessage.isEmpty()) {
        newMessage = "Unable to process your request \nPlease try again later !!"
    }

    showToast(newMessage)
//    AlertMessageDialog.newInstance(newMessage)
//        .show(requireActivity().supportFragmentManager, AlertMessageDialog.TAG)
}

fun Fragment.checkPermission() {
    val permissionArray = arrayOf(Manifest.permission.CAMERA)
    val dialogBinding = CustomAlertDialogBinding.inflate(requireActivity().layoutInflater)
    val alertDialog = AlertDialog.Builder(requireContext()).setView(dialogBinding.root).setCancelable(true).create()


    dialogBinding.btnYes.setOnClickListener()
    {
        ActivityCompat.requestPermissions(requireActivity(), permissionArray, CONSTANTS.CAMERA_PERMISSION_CODE)
        alertDialog.dismiss()
    }
  dialogBinding.btnClose.setOnClickListener()
    {
      alertDialog.dismiss()
    }





    if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.CAMERA)) {
        dialogBinding.title.text = getString(R.string.camera_permission)
        dialogBinding.body.text = getString(R.string.permission_dialog_description)
        alertDialog.show()
    } else {
        ActivityCompat.requestPermissions(requireActivity(), permissionArray, CONSTANTS.CAMERA_PERMISSION_CODE)
    }

}




