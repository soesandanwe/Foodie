package com.soesan.foodie.Fragment

import android.view.animation.DecelerateInterpolator
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.soesan.foodie.Adapter.MostPopularAdapter
import com.soesan.foodie.R
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.*
import com.soesan.foodie.UI.AdvancedNestedScrollView
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.DividerItemDecoration


class MostPopularFragment : Fragment() {

    var isShowingCardHeaderShadow=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View=inflater.inflate(R.layout.fragment_most_popular, container, false)

        val nestedScrollView =view.findViewById<AdvancedNestedScrollView>(R.id.bottom_sheet)
        val cardHeaderView=view.findViewById<View>(R.id.divider)


        val recyclerViewPopular = view.findViewById<RecyclerView>(R.id.recyclerView_mostpopular)
        val mostPopularAdapter = MostPopularAdapter(20,activity!!.baseContext)
        val lm=LinearLayoutManager(activity!!.baseContext)
        recyclerViewPopular.layoutManager = lm
        recyclerViewPopular.adapter = mostPopularAdapter
        recyclerViewPopular.addItemDecoration(DividerItemDecoration(activity!!.baseContext, lm.orientation))
        recyclerViewPopular.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val isRecyclerViewScrolledToTop=lm.findFirstVisibleItemPosition() ==0 && lm.findViewByPosition(0)?.top == 0
                if(!isRecyclerViewScrolledToTop && !isShowingCardHeaderShadow)
                {
                    isShowingCardHeaderShadow=true
                    showOrhideView(cardHeaderView,isShowingCardHeaderShadow)
                }
                else
                {
                    isShowingCardHeaderShadow=false
                    showOrhideView(cardHeaderView,isShowingCardHeaderShadow)
                }
            }
        })

        nestedScrollView.overScrollMode=View.OVER_SCROLL_NEVER
        nestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if(scrollY==0 && oldScrollX>0)
            {
                recyclerViewPopular.scrollToPosition(0)
            }
        })

         return view
    }

    private fun showOrhideView(cardHeaderView: View?, showingCardHeaderShadow: Boolean) {
        if (cardHeaderView != null)
            cardHeaderView.animate().alpha(if (showingCardHeaderShadow) 1f else 0f).setDuration(100).interpolator = DecelerateInterpolator()
    }


}
