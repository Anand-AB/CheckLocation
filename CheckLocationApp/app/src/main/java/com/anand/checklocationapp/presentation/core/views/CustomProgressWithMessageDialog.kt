package com.anand.checklocationapp.presentation.core.views

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.anand.checklocationapp.R

/**
 * Created by Anand A <anandabktda@gmail.com>
 * */

class CustomProgressWithMessageDialog(context: Context) : Dialog(context, R.style.full_screen_dialog) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_dialog_progress_withmessage)

        setCancelable(false)
    }
}