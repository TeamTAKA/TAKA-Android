package com.taka.taka.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.taka.taka.databinding.ItemTicketCardBinding

class TicketCardAdapter : RecyclerView.Adapter<TicketCardAdapter.ViewHolder>() {
    private val imageList: ArrayList<String> = arrayListOf()

    inner class ViewHolder(private val binding: ItemTicketCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(url: String) {

//            Glide
//                .with(binding.ticketCardIvPoster)
//                .load(url)
//                .into(binding.ticketCardIvPoster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTicketCardBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(imageList[position])
    }

    override fun getItemCount(): Int = imageList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setImageList(newImageList: ArrayList<String>) {
        imageList.clear()
        imageList.addAll(newImageList)
        notifyDataSetChanged()
    }
}