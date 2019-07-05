package com.soesan.foodie.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.soesan.foodie.Adapter.MostPopularAdapter
import com.soesan.foodie.R
import android.support.v7.widget.*
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout


class MostPopularFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View=inflater.inflate(R.layout.fragment_most_popular, container, false)

        val recyclerViewPopular = view.findViewById<RecyclerView>(R.id.recyclerView_mostpopular)
        val mostPopularAdapter = MostPopularAdapter(20,activity!!.baseContext)
        val lm=LinearLayoutManager(activity!!.baseContext,LinearLayout.HORIZONTAL,false)
        recyclerViewPopular.layoutManager = lm
        recyclerViewPopular.adapter = mostPopularAdapter
        recyclerViewPopular.setHasFixedSize(true)

        return view
    }


}
