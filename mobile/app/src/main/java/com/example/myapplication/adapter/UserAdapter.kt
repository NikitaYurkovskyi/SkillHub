package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ChatsPageBinding
import com.example.myapplication.databinding.ListUserBinding
import com.example.myapplication.retrofit.OtherUserProfile


class UserAdapter: ListAdapter<OtherUserProfile, UserAdapter.Holder>(Comparator()) {
    class Holder(view: View): RecyclerView.ViewHolder(view){
        private val binding = ListUserBinding.bind(view)

        fun bind(otherUserProfile: OtherUserProfile) = with(binding){
            userNick.text = otherUserProfile.nickname
        }
    }

    class Comparator: DiffUtil.ItemCallback<OtherUserProfile>(){
        override fun areItemsTheSame(
            oldItem: OtherUserProfile,
            newItem: OtherUserProfile
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: OtherUserProfile,
            newItem: OtherUserProfile
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_user, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }
}