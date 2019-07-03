package com.soesan.foodie.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

import com.soesan.foodie.R
import com.soesan.foodie.UI.TopPanelButtonView
import kotlinx.android.synthetic.main.fragment_home.*
import android.support.v4.view.ViewCompat.setNestedScrollingEnabled
import android.support.v7.widget.LinearLayoutManager
import android.R.attr.country
import android.support.v7.widget.RecyclerView
import com.soesan.foodie.Adapter.RecommendedItemHomeAdapter


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View=inflater.inflate(R.layout.fragment_home, container, false)
        val btn_popular: TopPanelButtonView = view.findViewById(R.id.btn_popular)
        btn_popular.setOnClickListener{ view ->  Toast.makeText(context,"Hello",Toast.LENGTH_LONG).show()}


        val recyclerViewRecommend = view.findViewById<RecyclerView>(R.id.recyclerView_recommend_itemview)
        val recommendItemHomeAdapter = RecommendedItemHomeAdapter(3,activity!!.baseContext)
        recyclerViewRecommend.layoutManager = LinearLayoutManager(activity!!.baseContext, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewRecommend.adapter = recommendItemHomeAdapter
        recyclerViewRecommend.setHasFixedSize(true)
        recyclerViewRecommend.isNestedScrollingEnabled = false

        return  view
    }




}
