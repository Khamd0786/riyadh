package com.hammad.riyadh.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hammad.riyadh.databinding.ItemEventBinding
import com.hammad.riyadh.models.Event

class EventAdapter : ListAdapter<Event, EventAdapter.VH>(Comparator) {

    inner class VH(private val binding: ItemEventBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Event) {

            binding.bottomHeader.apply {
                tvPlace.text = item.placeName
                tvName.text = item.name
                tvPriceRange.text = "$${item.priceFrom} - $${item.priceTo}"
            }

            Glide.with(binding.root.context).load(item.imageUrl).into(binding.image)

            binding.containerDate.apply {
                tvDateFrom.text = item.dateFrom
                tvDateTo.text = item.dateTo
            }

            binding.containerType.apply {
                tvType.text = item.eventType
                Glide.with(root.context).load(item.typeImage).into(ivImage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }


    private companion object Comparator : DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.name == newItem.name
                    && oldItem.eventType == newItem.eventType
                    && oldItem.dateFrom == newItem.dateFrom
                    && oldItem.dateTo == newItem.dateTo
                    && oldItem.priceFrom == newItem.priceFrom
                    && oldItem.priceTo == newItem.priceTo
        }

    }
}