package com.example.travelo.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.travelo.R
import com.example.travelo.adapter.AdapterTravel.HolderTravel
import com.example.travelo.model.ModelTravel
import com.squareup.picasso.Picasso

class AdapterTravel(private val travelLists: ArrayList<ModelTravel>) :
    RecyclerView.Adapter<HolderTravel>() {

    private lateinit var  onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderTravel {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.layout_travel, parent, false)
        return HolderTravel(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: HolderTravel, position: Int) {
        val (_, _, name, location, image, description, _, _) = travelLists[position]
        holder.tvName.text = "Name : $name"
        holder.tvLocation.text = "Location : $location"
        holder.tvDesc.text = "Description : $description"
        Picasso.get().load(image).noFade().into(holder.imgTravel)

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(travelLists[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int {
        return travelLists.size
    }

    class HolderTravel(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.travelName)
        val tvLocation: TextView = itemView.findViewById(R.id.travelLocation)
        val tvDesc: TextView = itemView.findViewById(R.id.travelDesc)
        val imgTravel: ImageView = itemView.findViewById(R.id.travelImg)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ModelTravel)
    }
}