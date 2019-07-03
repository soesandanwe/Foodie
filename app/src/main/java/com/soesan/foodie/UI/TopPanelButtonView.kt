package com.soesan.foodie.UI

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.soesan.foodie.R

class TopPanelButtonView(context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {

    init {
        inflate(context, R.layout.top_panel_button_view, this)

        val imageView: ImageView = findViewById(R.id.image)
        val textView: TextView = findViewById(R.id.caption)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.TopPanelButtonView)
        imageView.setImageDrawable(attributes.getDrawable(R.styleable.TopPanelButtonView_image))
        textView.setText(attributes.getString(R.styleable.TopPanelButtonView_text))
        attributes.recycle()

    }
}