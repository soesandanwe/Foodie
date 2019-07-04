package com.soesan.foodie.Adapter

import android.content.Context
import android.content.res.Resources
import android.support.v4.content.ContextCompat

import android.support.v7.widget.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.soesan.foodie.R

import kotlinx.android.synthetic.main.cardview_shop_shortdesc.view.*


class RecommendedItemHomeAdapter(val items :Int, val context: Context) : RecyclerView.Adapter<RecommendedItemHomeAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview_recommended, p0, false))
    }
    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        p0?.imgv_shop.setImageResource(R.drawable.food1)
        p0?.tv_shop_name?.text = "Famous Shop " + p1
        p0?.rb_shop.rating=(p1+0.5).toFloat()
        p0?.tv_like_count?.text = (100 + p1).toString()
        p0?.tv_price?.text = "$ " + p1+30
        p0?.tv_shop_type?.text = "Western Food " + p1


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

    }
}