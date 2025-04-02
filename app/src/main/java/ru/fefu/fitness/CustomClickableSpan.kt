package ru.fefu.fitness

import android.content.Context
import android.os.Build
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View
import androidx.annotation.RequiresApi

class CustomClickableSpan(
    private val ctx: Context,
    private val clickHandler: () -> Unit
) : ClickableSpan() {

    override fun onClick(view: View) {
        clickHandler()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun updateDrawState(textPaint: TextPaint) {
        super.updateDrawState(textPaint)
        textPaint.apply {
            isUnderlineText = false
            color = ctx.getColor(R.color.purple)
        }
    }
}
