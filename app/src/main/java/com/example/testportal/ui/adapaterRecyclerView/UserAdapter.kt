package com.example.testportal.ui.adapaterRecyclerView

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testportal.R
import com.example.testportal.databinding.PersonItemBinding
import com.example.testportal.network.response.DataItem

class UserAdapter:  PagingDataAdapter<DataItem, UserAdapter.ViewHolder>(DIFF_CALLBACK) {

    private var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(data: DataItem)
    }

    fun setOnItemClickCallback(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    class ViewHolder(private val binding: PersonItemBinding) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(preview: DataItem, onItemClickCallback: OnItemClickListener) {
            binding.root.setOnClickListener {
                onItemClickCallback.onItemClick(preview)
            }

            binding.userName.text = preview.firstName + " " + preview.lastName
            binding.email.text = preview.email
            if (preview.avatar != null){
                Glide.with(binding.photoProfile.context).load(preview.avatar).centerCrop()
                    .into(binding.photoProfile)
            } else {
                binding.photoProfile.setImageResource(R.drawable.baseline_person_24)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PersonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item, onItemClickListener!!)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }
        }
    }

}