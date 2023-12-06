package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ChatMessageBinding
import com.example.myapplication.databinding.OwnMessageBinding
import com.example.myapplication.retrofit.Message
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ChatMessageAdapter: ListAdapter<Message, ChatMessageAdapter.Holder>(Comparator()) {

    private var nickname: String = ""

    fun setNickname(nickname: String) {
        this.nickname = nickname
        notifyDataSetChanged()
    }


    inner class Holder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = ChatMessageBinding.bind(view)

        fun bind(message: Message) = with(binding) {
            if (message.user.nickname == nickname) {
                messageBody.text = message.text
                senderName.visibility = View.INVISIBLE
                imageView13.visibility = View.INVISIBLE
            } else {
                messageBody.text = message.text
                senderName.visibility = View.VISIBLE
                senderName.text = message.user.nickname
            }

        }
    }

    class Comparator: DiffUtil.ItemCallback<Message>(){
        override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
            return oldItem.id == newItem.id;
        }

        override fun areContentsTheSame(
            oldItem: Message,
            newItem: Message
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