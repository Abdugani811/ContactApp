package com.example.contactapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import com.example.contactapp.R
import com.example.contactapp.databinding.UserListItemLayoutBinding
import com.example.contactapp.models.UserModel

class UserListItemAdapter(context: Context, list: List<UserModel>) :
    ArrayAdapter<UserModel>(context, R.layout.user_list_item_layout, list) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var binding: UserListItemLayoutBinding = if (convertView == null) {
            UserListItemLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        } else {
            UserListItemLayoutBinding.bind(convertView)
        }
        
        val user = getItem(position)
        if (user != null) {
            binding.userImg.foreground =
                ContextCompat.getDrawable(binding.root.context, user.userImg)
            binding.userName.text = user.name
            binding.userPhone.text = user.mobilePhoneNumber
        }

        return binding.root
    }
}