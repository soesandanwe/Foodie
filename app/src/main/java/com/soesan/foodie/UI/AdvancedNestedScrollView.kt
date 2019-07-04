package com.soesan.foodie.UI

import android.content.Context
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View

open class AdvancedNestedScrollView(context: Context, attrs: AttributeSet?) : CustomNestedScrollView(context, attrs) {
    private fun isRvScrolledToTop(rv: RecyclerView): Boolean {
        val lm = rv.layoutManager as LinearLayoutManager?
        return lm!!.findFirstVisibleItemPosition() == 0 && lm.findViewByPosition(0)!!.top == 0
    }

    private fun isNsvScrolledToBottom(nsv: NestedScrollView): Boolean {
        return !nsv.canScrollVertically(1)
    }

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        val rv = target as RecyclerView
        if (dy < 0 && isRvScrolledToTop(rv) || dy > 0 && !isNsvScrolledToBottom(this)) {
            scrollBy(0, dy)
            consumed[1] = dy
            return
        }
        super.onNestedPreScroll(target, dx, dy, consumed, type)
    }
}