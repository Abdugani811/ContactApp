package com.example.contactapp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactapp.adapters.LastSeenUserAdapter
import com.example.contactapp.adapters.UserListItemAdapter
import com.example.contactapp.databinding.ActivityMainBinding
import com.example.contactapp.models.UserModel
import com.example.contactapp.screens.UserProfileActivity
import kotlin.jvm.java
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var list = arrayListOf<UserModel>()
    private var listOfImages = arrayListOf<Int>()
    private lateinit var lastSeenAdapter: LastSeenUserAdapter
    private lateinit var userListAdapter: UserListItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadData()
        loadUI()
        loadLastSeenUsers()
    }

    private fun loadLastSeenUsers() {
        lastSeenAdapter = LastSeenUserAdapter(list)
        var myLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        binding.lastSeenUsersList.apply {
            adapter = lastSeenAdapter
            layoutManager = myLayoutManager
        }

//    letters list
        val uppercaseLetters = listOf<String>(
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"
        )
//        letter scroll
        binding.lettersScroll.adapter =
            ArrayAdapter<String>(this, R.layout.letter_item, uppercaseLetters)
    }

    private fun filter(text: String?) {
        val filteredList = arrayListOf<UserModel>()
        if (text.isNullOrEmpty()) {
            filteredList.addAll(list)
        } else {
            for (item in list) {
                if (item.name.lowercase().contains(text.lowercase())) {
                    filteredList.add(item)
                }
            }
        }

        lastSeenAdapter.updateList(filteredList)
        
        userListAdapter.clear()
        userListAdapter.addAll(filteredList)
        userListAdapter.notifyDataSetChanged()
    }

    private fun loadData() {
        listOfImages.add(R.drawable.img1)
        listOfImages.add(R.drawable.img2)
        listOfImages.add(R.drawable.img3)
        listOfImages.add(R.drawable.img4)
        listOfImages.add(R.drawable.img5)
        listOfImages.add(R.drawable.img1)
        listOfImages.add(R.drawable.img2)
        listOfImages.add(R.drawable.img3)
        listOfImages.add(R.drawable.img4)
        listOfImages.add(R.drawable.img5)
        listOfImages.add(R.drawable.img1)
        listOfImages.add(R.drawable.img2)
        listOfImages.add(R.drawable.img3)
        listOfImages.add(R.drawable.img4)
        listOfImages.add(R.drawable.img5)
        var randomPhoneNumber = Random.nextInt(1000)
        var lastSeenMinutes = Random.nextInt(59)
        for (i in 0..9) {
            list.add(
                UserModel(
                    "Kavin  ${if (i / 2 == 0) "Jane" else "Kavin"}",
                    listOfImages[i],
                    "+99891575$randomPhoneNumber",
                    "+99891575$randomPhoneNumber",
                    "998915756224$randomPhoneNumber",
                    if (i / 2 == 0) "44 Mins ago" else "32 Mins ago"
                )
            )
        }
    }

    private fun loadUI() {

        userListAdapter = UserListItemAdapter(this@MainActivity, list)
        binding.usersList.adapter = userListAdapter
        
        binding.search.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText)
                return true
            }
        })

        binding.usersList.setOnItemClickListener { adapterView, view, i, l ->
            val user = userListAdapter.getItem(i)
            if (user != null) {
                val intent = Intent(this, UserProfileActivity::class.java)
                intent.putExtra("name", user.name)
                intent.putExtra("img", user.userImg)
                intent.putExtra("home", user.homePhoneNumber)
                startActivity(intent)
            }
        }
    }
}