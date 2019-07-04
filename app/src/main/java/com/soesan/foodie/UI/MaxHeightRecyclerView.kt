package com.soesan.foodie.UI

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

open class MaxHeightRecyclerView(context: Context, attrs: AttributeSet) : RecyclerView(context, attrs) {
    private var mMaxHeight = -1

    override fun onMeasure(widthSpec: Int, heightSpec: Int) {
        var heightSpec = heightSpec
        val mode = View.MeasureSpec.getMode(heightSpec)
        val height = View.MeasureSpec.getSize(heightSpec)
        if (mMaxHeight >= 0 && (mode == View.MeasureSpec.UNSPECIFIED || height > mMaxHeight)) {
            heightSpec = View.MeasureSpec.makeMeasureSpec(mMaxHeight, View.MeasureSpec.AT_MOST)
        }
        super.onMeasure(widthSpec, heightSpec)
    }

    /**
     * Sets the maximum height for this recycler view.
     */
    fun setMaxHeight(maxHeight: Int) {
        if (mMaxHeight != maxHeight) {
            mMaxHeight = maxHeight
            requestLayout()
        }
    }
}