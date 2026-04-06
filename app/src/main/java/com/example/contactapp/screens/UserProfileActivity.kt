package com.example.contactapp.screens

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.contactapp.R
import com.example.contactapp.databinding.ActivityUserProfileBinding
import com.example.contactapp.models.UserModel
import kotlin.random.Random

class UserProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserProfileBinding
    private var list = ArrayList<UserModel>()
    private var listOfImages = ArrayList<Int>()
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        loadData()
        loadUI()
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
                    "User$i",
                    listOfImages[i],
                    "+99893788$randomPhoneNumber",
                    "+7999355$randomPhoneNumber",
                    "+7988875$randomPhoneNumber",
                    "$lastSeenMinutes ago"
                )
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun loadUI() {
        binding.backImg.setOnClickListener() {
            finish()
        }

        var img = intent.getIntExtra("img", 0)
        var home = intent.getStringExtra("home")
        binding.profileImg.foreground = ContextCompat.getDrawable(this, img)
        binding.profileNameTv.text = home
        binding.textView.text = home
        binding.phoneNumberTv.text = home
    }
}