package com.soesan.foodie.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.soesan.foodie.R
import kotlinx.android.synthetic.main.cardview_emotions.view.*

class EmotionsAdapter(val items :Int, val context: Context) : RecyclerView.Adapter<EmotionsAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview_emotions, p0, false))
    }
    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {

        when(p1) {
            0 -> {
                p0?.imgv_emoticon.setImageResource(R.drawable.ic_twotone_sentiment_satisfied_24px)
                p0?.tv_emoticon?.text = "Enjoy"
                }
            1 -> {
                p0?.imgv_emoticon.setImageResource(R.drawable.ic_favorite_black_24dp)
                p0?.tv_emoticon?.text = "Love it"
            }
            2 -> {
                p0?.imgv_emoticon.setImageResource(R.drawable.ic_twotone_fastfood_24px)
                p0?.tv_emoticon?.text = "Recommended"
            }

            else -> {
                p0?.imgv_emoticon.setImageResource(R.drawable.ic_twotone_thumb_up_alt_24px)
                p0?.tv_emoticon?.text = ""
            }
        }



    }
    override fun getItemCount(): Int {
        return items
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val imgv_emoticon=view.imgv_emoticon
        val tv_emoticon = view.tv_emoticon


    }
}