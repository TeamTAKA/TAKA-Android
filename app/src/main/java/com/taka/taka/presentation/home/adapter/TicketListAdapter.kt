package com.taka.taka.presentation.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.taka.taka.databinding.ItemTicketBoxBinding
import com.taka.taka.domain.model.Ticket

class TicketListAdapter(val itemClickListener: (Int) -> Unit) :
    RecyclerView.Adapter<TicketListAdapter.ViewHolder>() {
    private val tickets: ArrayList<Ticket> = arrayListOf()

    inner class ViewHolder(private val binding: ItemTicketBoxBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(ticket: Ticket) {
            binding.root.setOnClickListener { itemClickListener.invoke(ticket.id) }
            binding.ticketBoxTvDate.text = ticket.date

            Glide
                .with(binding.ticketBoxIvPoster)
                .load(ticket.img)
                .into(binding.ticketBoxIvPoster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTicketBoxBinding.inflate(
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