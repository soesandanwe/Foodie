package com.soesan.foodie.UI

import android.app.PendingIntent.getActivity
import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.ViewCompat
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.soesan.foodie.R



class CustomBehavior(context: Context, attrs: AttributeSet): CoordinatorLayout.Behavior<NestedScrollView>() {

    override fun layoutDependsOn(parent: CoordinatorLayout, child: NestedScrollView, dependency: View): Boolean {
        return dependency.id==R.id.toolbar_container
    }

    override fun onLayoutChild(parent: CoordinatorLayout, child: NestedScrollView, layoutDirection: Int): Boolean {
        parent.onLayoutChild(child,layoutDirection)
        setTopMargin(child.findViewById<CardView>(R.id.card_view))
        val rvMaxHeight=child.height
        val rv=child.findViewById<MaxHeightRecyclerView>(R.id.recyclerView_mostpopular)
        rv.setMaxHeight(rvMaxHeight)

        val cardContainer=child.findViewById<View>(R.id.card_container)

        val toolbarContainerHeight=parent.getDependencies(child).get(0).height
        setPaddingTop(cardContainer,rvMaxHeight-toolbarContainerHeight)
        ViewCompat.offsetTopAndBottom(child,toolbarContainerHeight)
        setPaddingBottom(rv,toolbarContainerHeight)
        return true
    }

    private fun setPaddingBottom(view: View?, bottom: Int) {
        if (view != null) {
            if(view.paddingTop!=bottom)
            {
                view.setPadding(view.paddingLeft,view.paddingTop,view.paddingRight,bottom)
            }
        }
    }

    private fun setPaddingTop(view: View?, top: Int) {
        if (view != null) {
            if(view.paddingTop!=top)
            {
                view.setPadding(view.paddingLeft,top,view.paddingRight,view.paddingBottom)
            }
        }
    }

    private fun setTopMargin(view: View) {
       val lp=view.layoutParams as ViewGroup.MarginLayoutParams
        if(lp.topMargin != 100)
        {
            lp.topMargin=100
            view.layoutParams=lp
        }

    }

    override fun onInterceptTouchEvent(parent: CoordinatorLayout, child: NestedScrollView, ev: MotionEvent): Boolean {
        return ev.actionMasked==MotionEvent.ACTION_DOWN && isTouchChildBounds(parent,child,ev) && !isTouchChildBounds(parent,child.findViewById(R.id.card_view),ev)

    }

    private fun isTouchChildBounds(parent: ViewGroup, child: View, ev: MotionEvent): Boolean {
        return MyViewGroupUtils.isPointInChildBounds(parent,child,ev.getX().toInt(),ev.getY().toInt())
    }
}