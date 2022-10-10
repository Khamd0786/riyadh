package com.hammad.riyadh.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hammad.riyadh.R
import com.hammad.riyadh.databinding.ItemTabBinding
import com.hammad.riyadh.models.TabItem

class TabAdapter(private val callback: SelectListener) :
    ListAdapter<TabItem, TabAdapter.VH>(Comparator) {

    private var oldSelectedPosition: Int = 0
    private var selectedType = "All"


    inner class VH(private val binding: ItemTabBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TabItem, position: Int) {

            item.isSelected = (item.tab.lowercase() == selectedType.lowercase())

            binding.root.isSelected = item.isSelected.also {
                binding.tvTab.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        if (it) R.color.white else R.color.colorPrimary
                    )
                )
            }

            binding.root.setOnClickListener { callback.onTabSelected(item, position) }
            binding.tvTab.text = item.tab
        }
    }

    fun selectedTopic(type: String, newPosition: Int) {
        selectedType = type
        notifyItemChanged(newPosition)
        notifyItemChanged(oldSelectedPosition)
        this.oldSelectedPosition = newPosition
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemTabBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position), position)
    }

    private object Comparator : DiffUtil.ItemCallback<TabItem>() {

        override fun areItemsTheSame(oldItem: TabItem, newItem: TabItem): Boolean {
            return oldItem.tab == newItem.tab
        }

        override fun areContentsTheSame(oldItem: TabItem, newItem: TabItem): Boolean {
            return oldItem.tab == newItem.tab && oldItem.isSelected == newItem.isSelected
        }
    }

    interface SelectListener {
        fun onTabSelected(item: TabItem, position: Int)
    }
}