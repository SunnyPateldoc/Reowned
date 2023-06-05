package com.example.reowned.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.reowned.R
import com.example.reowned.model.Product

class AdapterProduts(val list: ArrayList<Product>):RecyclerView.Adapter<AdapterProduts.ViewHolder>() {


    class ViewHolder(item : View) : RecyclerView.ViewHolder(item) {

        val image : ImageView
        val id : TextView
        val title : TextView
        val description : TextView
        val price : TextView


        init {

            image = item.findViewById(R.id.img_product)
            id = item.findViewById(R.id.text_id)
            title = item.findViewById(R.id.text_title)
            description = item.findViewById(R.id.text_description)
            price = item.findViewById(R.id.text_price)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.products_layout,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val pos = list[position]

        Glide.with(holder.itemView.context).load(pos.images[0]).into(holder.image)

        holder.id.setText("${pos.id}")
        holder.title.setText("${pos.title}")
        holder.description.setText("${pos.description}")
        holder.price.setText("${pos.price}")


    }


}