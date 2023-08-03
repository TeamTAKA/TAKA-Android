package com.taka.taka.presentation.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.taka.taka.R
import com.taka.taka.databinding.ItemTicketGroupBinding
import com.taka.taka.domain.model.TicketGroup

class TicketGroupAdapter(val itemClickListener: (Int) -> Unit) :
    RecyclerView.Adapter<TicketGroupAdapter.ViewHolder>() {
    private val ticketGroups: ArrayList<TicketGroup> = arrayListOf()

    inner class ViewHolder(private val binding: ItemTicketGroupBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(ticketGroup: TicketGroup) {
            binding.ticketGroupTvTitle.text = ticketGroup.titleKr
            binding.ticketGroupRvTickets.adapter =
                TicketListAdapter { ticketId -> itemClickListener.invoke(ticketId) }.apply {
                    setTicketList(ticketGroup.tickets)
                }
            binding.ticketGroupLlTitle.setOnClickListener {
                binding.ticketGroupRvTickets.isVisible = !binding.ticketGroupRvTickets.isVisible
                binding.ticketGroupIvArrow.setImageResource(
                    if (binding.ticketGroupRvTickets.isVisible) R.drawable.ic_list_close
                    else R.drawable.ic_list_open
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTicketGroupBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(ticketGroups[position])
    }

    override fun getItemCount(): Int = ticketGroups.size

    @SuppressLint("NotifyDataSetChanged")
    fun setTicketGroupList(newTicketGroupList: List<TicketGroup>) {
        ticketGroups.clear()
        ticketGroups.addAll(newTicketGroupList)
        notifyDataSetChanged()
    }
}