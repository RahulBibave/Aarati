package com.rahul.aarati

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class AdapterGallery (val mContext :Context, val arrayList :ArrayList<Int>) : RecyclerView.Adapter<AdapterGallery.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(mContext).inflate(R.layout.photoview,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.init()
        holder.Image!!.setImageDrawable(mContext.getResources().getDrawable(arrayList.get(position)));
    }


    inner class ViewHolder(itemView :View):RecyclerView.ViewHolder(itemView) {
          var Image:ImageView?=null
           fun init(){
               Image=itemView.findViewById(R.id.imageView)
           }
    }
}