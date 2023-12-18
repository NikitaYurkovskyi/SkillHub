package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ChatMessageBinding
import com.example.myapplication.retrofit.ForumMessageDto
import com.example.myapplication.retrofit.Message

class ForumMessageAdapter: ListAdapter<ForumMessageDto, ForumMessageAdapter.Holder>(ForumMessageAdapter.Comparator()) {
    private var nickname: String = ""

    fun setNickname(nickname: String) {
        this.nickname = nickname
        notifyDataSetChanged()
    }


    inner class Holder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = ChatMessageBinding.bind(view)

        fun bind(forumMessageDto: ForumMessageDto) = with(binding) {
            if (forumMessageDto.user.id == "628b95b1-b5a1-4b05-898d-f64cc86fc0c2") {
                messageBody.text = forumMessageDto.text
                senderName.visibility = View.VISIBLE
                senderName.text = "yen"
            } else if (forumMessageDto.user.id == "779acdc3-8055-440e-9875-31caa03e8a2d") {
                messageBody.text = forumMessageDto.text
                senderName.visibility = View.VISIBLE
                senderName.text = "triss"
            } else {
                messageBody.text = forumMessageDto.text
                senderName.visibility = View.INVISIBLE
                imageView13.visibility = View.INVISIBLE
            }
        }
    }

    class Comparator: DiffUtil.ItemCallback<ForumMessageDto>(){
        override fun areItemsTheSame(oldItem: ForumMessageDto, newItem: ForumMessageDto): Boolean {
            return oldItem.id == newItem.id;
        }

        override fun areContentsTheSame(
            oldItem: ForumMessageDto,
            newItem: ForumMessageDto
        ): Boolean {
            return oldItem == newItem;
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.chat_message, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }
}