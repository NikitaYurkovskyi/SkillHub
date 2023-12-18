package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ForumListBinding
import com.example.myapplication.retrofit.GetForumsDto

class AllForumsAdapter: ListAdapter<GetForumsDto, AllForumsAdapter.Holder>(Comparator()) {
    private lateinit var onForumClickListener: (String) -> Unit
    fun setOnChatClickListener(onChatClickListener: (String) -> Unit) {
        this.onForumClickListener = onChatClickListener
    }
    inner class Holder (view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ForumListBinding.bind(view)
        fun bind(getForumsDto: GetForumsDto) = with(binding) {
            allForumsForumTitle.text = getForumsDto.theme
            allForumsForumDescription.text= getForumsDto.description

            itemView.setOnClickListener{onForumClickListener(getForumsDto.id)}
        }
    }
    class Comparator :DiffUtil.ItemCallback<GetForumsDto>() {
        override fun areItemsTheSame(oldItem: GetForumsDto, newItem: GetForumsDto): Boolean {
            return oldItem.id === newItem.id
        }

        override fun areContentsTheSame(oldItem: GetForumsDto, newItem: GetForumsDto): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.forum_list, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }
}