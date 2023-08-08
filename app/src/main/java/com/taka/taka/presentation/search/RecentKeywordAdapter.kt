package com.taka.taka.presentation.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.taka.taka.databinding.ItemSearchKeywordBinding

class RecentKeywordAdapter(
    val itemClickListener: (String) -> Unit,
    val deleteClickListener: (String) -> Unit,
) : RecyclerView.Adapter<RecentKeywordAdapter.ViewHolder>() {
    private val keywords: ArrayList<String> = arrayListOf()

    inner class ViewHolder(private val binding: ItemSearchKeywordBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(keyword: String) {
            binding.root.setOnClickListener { itemClickListener(keyword) }
            binding.ivDelete.setOnClickListener { deleteClickListener(keyword) }
            binding.tvKeyword.text = keyword
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemSearchKeywordBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(keywords[position])
    }

    override fun getItemCount(): Int = keywords.size

    @SuppressLint("NotifyDataSetChanged")
    fun setKeywordList(keywordList: List<String>) {
        keywords.clear()
        keywords.addAll(keywordList)
        notifyDataSetChanged()
    }
}