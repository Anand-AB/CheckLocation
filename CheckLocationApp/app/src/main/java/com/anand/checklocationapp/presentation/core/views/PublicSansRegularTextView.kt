package com.anand.checklocationapp.presentation.core.views

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.anand.checklocationapp.presentation.utility.AppConstants.Companion.PUBLIC_SANS_TTF

class PublicSansRegularTextView : AppCompatTextView {
    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        val font: Typeface = Typeface.createFromAsset(context.assets, PUBLIC_SANS_TTF)
        this.setTypeface(font, Typeface.NORMAL)
    }
}