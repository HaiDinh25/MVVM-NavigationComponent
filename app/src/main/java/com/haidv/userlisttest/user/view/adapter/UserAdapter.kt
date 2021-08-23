package com.haidv.userlisttest.user.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.haidv.userlisttest.databinding.ItemUserBinding
import com.haidv.userlisttest.user.data.User

class UserAdapter(private val context: Context, private val onClickItem: (User) -> Unit) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binItem(users[position])
    }

    override fun getItemCount(): Int = users.size

    inner class ViewHolder(private val itemUserBinding: ItemUserBinding) :
        RecyclerView.ViewHolder(itemUserBinding.root) {
        fun binItem(user: User) {
            itemUserBinding.user = user
            Glide.with(context)
                .load(user.avatar_url)
                .into(itemUserBinding.imageAvatar)
            itemUserBinding.layoutItem.setOnClickListener { onClickItem(user) }
        }
    }

    var users: List<User> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
}