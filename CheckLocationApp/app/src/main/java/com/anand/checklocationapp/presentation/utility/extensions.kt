package com.anand.checklocationapp.presentation.utility

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import com.anand.checklocationapp.presentation.core.BaseActivity

fun Context.showDialog(
    title: String? = "App",
    msg: String,
    positiveText: String? = "OK",
    listener: DialogInterface.OnClickListener? = null,
    icon: Int? = null
) {
    if (BaseActivity.dialogShowing) {
        return
    }
    val builder = AlertDialog.Builder(this)
    builder.setTitle(title)
    builder.setMessage(msg)
    builder.setCancelable(false)
    builder.setPositiveButton(positiveText) { dialog, which ->
        BaseActivity.dialogShowing = false
        listener?.onClick(dialog, which)
    }
    if (icon != null) {
        builder.setIcon(icon)
    }
    builder.create().show()
    BaseActivity.dialogShowing = true
}