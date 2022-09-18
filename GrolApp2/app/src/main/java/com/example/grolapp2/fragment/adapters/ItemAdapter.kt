package com.example.grolapp2.fragment.adapters

import android.content.ClipData
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.grolapp2.databinding.ItemBinding
import com.example.grolapp2.model.Items

class ItemAdapter(): RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    private var items: List<Items> = listOf()

    class ViewHolder(private val binding: ItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Items) {
            binding.tvItemName.text = item.name
            binding.tvItemAmount.text = item.number
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(items: List<Items>) {
        this.items = items
        notifyDataSetChanged()
    }
}