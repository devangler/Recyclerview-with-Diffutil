package com.topzonestudio.recycleviewwithdiff.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.topzonestudio.recycleviewwithdiff.R
import com.topzonestudio.recycleviewwithdiff.User
import com.topzonestudio.recycleviewwithdiff.`interface`.OnItemClickListener
import com.topzonestudio.recycleviewwithdiff.databinding.ItemBinding


class AdaptorClass(private val onItemClickListener: OnItemClickListener) :
    ListAdapter<User, AdaptorClass.CustomViewHolderEmployee>(DiffUtilsEmployees) {

    object DiffUtilsEmployees : DiffUtil.ItemCallback<User>() {

        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }

    class CustomViewHolderEmployee(val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolderEmployee {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemBinding>(layoutInflater,
            R.layout.item,
            parent,
            false)
        return CustomViewHolderEmployee(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolderEmployee, position: Int) {
        val item = getItem(position)
        with(holder) {
            binding.apply {
                txt.text = item.name
                txt.setOnClickListener {
                    onItemClickListener.onItemClick(item)

                }
                imageView.setOnClickListener {
                    onItemClickListener.onDeleteItem(item)

                }

            }
        }
    }
}