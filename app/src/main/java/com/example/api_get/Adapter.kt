package com.example.api_get

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class Adapter (val images: List<String>) : RecyclerView.Adapter<Adapter.ViewHolder>(){
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = images[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item, parent, false))
    }

    override fun getItemCount(): Int {
        return images.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(image: String) {
            itemView.imageview.fromUrl(image)
        }
    }
}