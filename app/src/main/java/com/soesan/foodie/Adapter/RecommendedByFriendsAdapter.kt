package com.soesan.foodie.Adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Layout.JUSTIFICATION_MODE_INTER_WORD
import android.text.SpannableString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.soesan.foodie.R
import kotlinx.android.synthetic.main.cardview_recommended.view.imgv_shop
import kotlinx.android.synthetic.main.cardview_recommended.view.rb_shop
import kotlinx.android.synthetic.main.cardview_recommended.view.tv_like_count
import kotlinx.android.synthetic.main.cardview_recommended.view.tv_price
import kotlinx.android.synthetic.main.cardview_recommended.view.tv_shop_name
import kotlinx.android.synthetic.main.cardview_recommended.view.tv_shop_type

import kotlinx.android.synthetic.main.cardview_shop_shortdesc.view.tv_distance
import kotlinx.android.synthetic.main.cardview_shop_shortdesc.view.tv_status
import kotlinx.android.synthetic.main.cardview_testimonial.view.*
import android.text.style.ForegroundColorSpan
import android.text.Spannable

class RecommendedByFriendsAdapter(val items :Int, val context: Context) : RecyclerView.Adapter<RecommendedByFriendsAdapter.MyViewHolder>() {
var isCheck:Boolean=true

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview_testimonial, p0, false))
    }
    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        p0?.imgv_shop.setImageResource(R.drawable.food1)
        p0?.imgv_profile.setImageResource(R.drawable.exo)

        p0?.tv_profile_name?.text="Person "+p1
        p0?.tv_profil_status?.text= p1.toString()+"h ago"

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


        p0?.tv_review.text="Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."

        /* val spanStr:SpannableString=SpannableString(context.getString(R.string.ellipsis_end))
         val foregroundSpan = ForegroundColorSpan(context.resources.getColor(R.color.colorAccent))
         spanStr.setSpan(foregroundSpan,0,spanStr.length,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
         p0?.tv_review.setEllipsizeText(spanStr,0)

         p0?.tv_review.setEllipsizeText(spanStr,0)
         p0?.tv_review.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
       */
        p0?.tv_review.setOnClickListener { view ->   if (isCheck) {
            p0?.tv_review.setMaxLines(Int.MAX_VALUE);
            isCheck = false;
        } else {
            p0?.tv_review.setMaxLines(context.resources.getInteger(R.integer.maxLineCount));
            isCheck = true;
        } }




        //val recyclerViewEmotions = view.findViewById<RecyclerView>(R.id.recyclerView_emotions)
        val emotionsAdadpter = EmotionsAdapter(4,context)
        p0?.recyclerview_emotions.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        p0?.recyclerview_emotions.adapter = emotionsAdadpter
        p0?.recyclerview_emotions.setHasFixedSize(true)
        p0?.recyclerview_emotions.isNestedScrollingEnabled = false
    }
    override fun getItemCount(): Int {
        return items
    }

    private fun TextView.setOnClickListener() {

    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val imgv_shop=view.imgv_shop
        val imgv_profile=view.imgv_profile_picture

        val tv_profile_name=view.tv_profile_name
        val tv_profil_status=view.tv_profile_status
        val tv_review=view.tv_review

        val tv_shop_name = view.tv_shop_name
        val rb_shop = view.rb_shop
        val tv_like_count = view.tv_like_count
        val tv_price = view.tv_price
        val tv_shop_type = view.tv_shop_type
        val tv_distance=view.tv_distance
        val tv_status=view.tv_status
        val recyclerview_emotions=view.recyclerView_emotions

    }
}


