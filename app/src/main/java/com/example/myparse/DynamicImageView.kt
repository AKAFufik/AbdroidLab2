package com.example.myparse

import android.content.Context
import android.util.AttributeSet

class DynamicImageView(context: Context, attrs: AttributeSet) : androidx.appcompat.widget.AppCompatImageView(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val dynamic = this.getDrawable()

        if (dynamic != null) {
            val width = MeasureSpec.getSize(widthMeasureSpec)
            val height =
                Math.ceil((width * dynamic.getIntrinsicHeight().toFloat() / dynamic.getIntrinsicWidth()).toDouble())
                    .toInt()
            this.setMeasuredDimension(width, height)
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }
}
