package com.soesan.foodie.Adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.soesan.foodie.R
import kotlinx.android.synthetic.main.cardview_recommended.view.*
import kotlinx.android.synthetic.main.cardview_recommended.view.imgv_shop
import kotlinx.android.synthetic.main.cardview_recommended.view.rb_shop
import kotlinx.android.synthetic.main.cardview_recommended.view.tv_like_count
import kotlinx.android.synthetic.main.cardview_recommended.view.tv_price
import kotlinx.android.synthetic.main.cardview_recommended.view.tv_shop_name
import kotlinx.android.synthetic.main.cardview_recommended.view.tv_shop_type
import kotlinx.android.synthetic.main.cardview_shop_shortdesc.view.*
import android.support.design.widget.BottomSheetBehavior
import android.support.v4.widget.NestedScrollView

import android.support.annotation.NonNull





class MostPopularAdapter(val items :Int, val context: Context) : RecyclerView.Adapter<MostPopularAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview_shop_shortdesc, p0, false))
    }
    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        p0?.imgv_shop.setImageResource(R.drawable.food1)
        p0?.tv_shop_name?.text = "Famous Shop " + p1
        p0?.rb_shop.rating=(p1+0.5).toFloat()
        p0?.tv_like_count?.text = (100 + p1).toString()
        p0?.tv_price?.text = "$ " + p1+30
        p0?.tv_shop_type?.text = "Western Food " + p1
        p0?.tv_distance.text=(p1+(0.1*p1)).toString() + " km"

        if(p1%2==0)
        {
            p0?.tv_status.text=(context.getString(R.string.open))
            p0?.tv_status.setTextColor(ContextCompat.getColor(context,R.color.colorGreen))
        }
        else
        {

            p0?.tv_status.text=(context.getString(R.string.close))
            p0?.tv_status.setTextColor(ContextCompat.getColor(context,R.color.colorGray))
        }


    }
    override fun getItemCount(): Int {
        return items
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val imgv_shop=view.imgv_shop
        val tv_shop_name = view.tv_shop_name
        val rb_shop = view.rb_shop
        val tv_like_count = view.tv_like_count
        val tv_price = view.tv_price
        val tv_shop_type = view.tv_shop_type
        val tv_distance=view.tv_distance
        val tv_status=view.tv_status
        val cardview_popular=view.popularCard
    }
}