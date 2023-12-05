package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ListChatBinding
import com.example.myapplication.retrofit.ChatModel


class ChatAdapter(): ListAdapter<ChatModel, ChatAdapter.Holder>(Comparator()) {

    private var nickname: String = ""
    private lateinit var onChatClickListener: (String) -> Unit

    fun setNickname(nickname: String) {
        this.nickname = nickname
        notifyDataSetChanged()
    }

    fun setOnChatClickListener(onChatClickListener: (String) -> Unit) {
        this.onChatClickListener = onChatClickListener
    }

    inner class Holder(view: View): RecyclerView.ViewHolder(view){
        private val binding = ListChatBinding.bind(view)

        fun bind(chatModel: ChatModel) = with(binding){
            if (chatModel.members[0].nickname == nickname) {
                userNick.text = chatModel.members[1].nickname
            } else {
                userNick.text = chatModel.members[0].nickname
            }

            itemView.setOnClickListener{onChatClickListener(chatModel.chat.id)}
        }
    }

    class Comparator: DiffUtil.ItemCallback<ChatModel>(){
        override fun areItemsTheSame(
            oldItem: ChatModel,
            newItem: ChatModel
        ): Boolean {
            return oldItem.chat.id == newItem.chat.id
        }

        override fun areContentsTheSame(
            oldItem: ChatModel,
            newItem: ChatModel
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_chat, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }
}