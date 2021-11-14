package com.example.furortask.business.util

import android.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.furortask.R

private var dialog: AlertDialog? = null

val Fragment.progressDialog: AlertDialog?
    get() {
        if (dialog == null)
            context?.let {
                dialog = AlertDialog.Builder(it, R.style.WrapContentDialog)
                    .setView(R.layout.progress_dialog)
                    .create()
            }
        return dialog
    }