package com.example.contactapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapp.databinding.LastSeenUserItemLayoutBinding
import com.example.contactapp.models.UserModel

class LastSeenUserAdapter(var list: List<UserModel>) :
    RecyclerView.Adapter<LastSeenUserAdapter.MyViewHolder>() {
    
    fun updateList(newList: List<UserModel>) {
        list = newList
        notifyDataSetChanged()
    }

    inner class MyViewHolder(val lastSeenUserItemLayoutBinding: LastSeenUserItemLayoutBinding) :
        RecyclerView.ViewHolder(lastSeenUserItemLayoutBinding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = LastSeenUserItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var img = list[position].userImg
        var name = list[position].name.toString()
        var minutes = list[position].lastSeen
        holder.lastSeenUserItemLayoutBinding.userImg.foreground =
            ContextCompat.getDrawable(holder.itemView.context, img)
        holder.lastSeenUserItemLayoutBinding.minutes.text = minutes.toString()
        holder.lastSeenUserItemLayoutBinding.name.text = name.toString()
    }
}