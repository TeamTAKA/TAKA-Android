package com.taka.taka.presentation.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.taka.taka.R
import com.taka.taka.databinding.ItemTicketCardBinding
import com.taka.taka.domain.model.Ticket

class TicketCardAdapter(val itemClickListener: (Int) -> Unit) :
    RecyclerView.Adapter<TicketCardAdapter.ViewHolder>() {
    private val tickets: ArrayList<Ticket> = arrayListOf()

    inner class ViewHolder(private val binding: ItemTicketCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(ticket: Ticket) {
            binding.titleKr.text = ticket.titleKr
            binding.titleEng.text = ticket.titleEn.ifBlank { binding.root.context.getString(R.string.ticket_title_default) }
            binding.ticket.setOnClickListener { itemClickListener(ticket.id) }

            Glide
                .with(binding.ivPoster)
                .load(ticket.img)
                .into(binding.ivPoster)
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
        holder.bind(tickets[position])
    }

    override fun getItemCount(): Int = tickets.size

    @SuppressLint("NotifyDataSetChanged")
    fun setTicketList(newTicketList: List<Ticket>) {
        tickets.clear()
        tickets.addAll(newTicketList)
        notifyDataSetChanged()
    }
}