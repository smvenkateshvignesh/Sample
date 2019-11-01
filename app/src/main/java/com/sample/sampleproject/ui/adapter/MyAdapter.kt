package com.sample.sampleproject.ui.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sample.sampleproject.R
import com.sample.sampleproject.models.MyData

class MyAdapter(val mContext:Context,val myList: ArrayList<MyData.Row>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.inflate_list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(myList[holder.adapterPosition])
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgImage: ImageView = itemView.findViewById(R.id.imgImage)
        private val txtTitle: TextView = itemView.findViewById(R.id.txtTitle)
        private val txtDescription: TextView = itemView.findViewById(R.id.txtDescription)
        fun onBind(myData: MyData.Row) {
            Glide
                .with(imgImage.context)
                .load(myData.imageHref)
                .apply(RequestOptions().placeholder(getProgress()))
                .error(R.drawable.placeholder)
                .into(imgImage)
            if (myData.title != null) {
                txtTitle.text = myData.title
                txtTitle.visibility = View.VISIBLE
            } else {
                txtTitle.visibility = View.INVISIBLE
            }
            if (myData.description != null) {
                txtDescription.text = myData.description
                txtDescription.visibility = View.VISIBLE
            } else {
                txtDescription.visibility = View.GONE
            }

        }
    }
    fun getProgress(): CircularProgressDrawable {
        val circularProgressDrawable = CircularProgressDrawable(mContext)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.setColorSchemeColors(Color.GRAY)
        circularProgressDrawable.start()
        return circularProgressDrawable
    }
}