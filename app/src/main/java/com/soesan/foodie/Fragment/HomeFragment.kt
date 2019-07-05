package com.soesan.foodie.Fragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.soesan.foodie.R
import com.soesan.foodie.UI.TopPanelButtonView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.soesan.foodie.Adapter.RecommendedItemHomeAdapter
import com.soesan.foodie.Activity.MostPopularShops


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View=inflater.inflate(R.layout.fragment_home, container, false)
        val btn_popular: TopPanelButtonView = view.findViewById(R.id.btn_popular)
        btn_popular.setOnClickListener{ view ->   val intent = Intent(activity!!.baseContext, MostPopularShops::class.java)
            // start your next activity
            startActivity(intent)}


        val recyclerViewRecommend = view.findViewById<RecyclerView>(R.id.recyclerView_recommend_itemview)
        val recommendItemHomeAdapter = RecommendedItemHomeAdapter(3,activity!!.baseContext)
        recyclerViewRecommend.layoutManager = LinearLayoutManager(activity!!.baseContext, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewRecommend.adapter = recommendItemHomeAdapter
        recyclerViewRecommend.setHasFixedSize(true)
        recyclerViewRecommend.isNestedScrollingEnabled = false

        return  view
    }






}
