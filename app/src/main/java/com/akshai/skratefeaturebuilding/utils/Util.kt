package com.akshai.skratefeaturebuilding.utils

import android.widget.Toast
import androidx.fragment.app.Fragment

object Util {

    fun Fragment.toast(msg: String) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_SHORT).show()
    }

}