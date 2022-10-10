package com.hammad.riyadh.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hammad.riyadh.databinding.ItemEventBinding
import com.hammad.riyadh.models.Event
import com.hammad.riyadh.utils.DateUtils

class EventAdapter(private var baseContext: Context) : ListAdapter<Event, EventAdapter.VH>(Comparator) {

    inner class VH(private val binding: ItemEventBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Event) {

            binding.bottomHeader.apply {
                tvPlace.text = item.placeName
                tvName.text = baseContext.resources.getString(item.name)
                tvPriceRange.text = "$${item.priceFrom} - $${item.priceTo}"
            }

            Glide.with(baseContext).load(item.imageUrl).into(binding.image)

            binding.containerDate.apply {
                tvDateFrom.text = DateUtils.toNextLineDate(item.dateFrom)
                tvDateTo.text = DateUtils.toNextLineDate(item.dateTo)
            }

            binding.containerType.apply {
                tvType.text = item.eventType
                Glide.with(baseContext).load(item.typeImage).into(ivImage)
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

     fun setContent(baseContext: Context) {
        this.baseContext = baseContext
         notifyDataSetChanged()
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