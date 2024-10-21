package com.example.tugas8

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tugas8.databinding.ActivityMainBinding
import com.example.tugas8.fragments.LoginFragment
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private var fullname = ""
    private var email = ""
    private var password = ""
    private var nim = "23/516582/SV/22690"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        with(binding) {
            val TAB_TITLES = resources.getStringArray(R.array.tabs)
            viewPager.adapter = SectionsPagerAdapter(this@MainActivity)
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = TAB_TITLES[position]
            }.attach()
            viewPager.currentItem = 1
        }
    }

    fun register(fullname: String, email: String, password: String) {
        this.fullname = fullname
        this.email = email
        this.password = password

        binding.viewPager.currentItem = 0
    }

    fun login(email: String, password: String) {
        if (this.email.isEmpty()) {
            Toast.makeText(this, "Please register first", Toast.LENGTH_SHORT).show()
            binding.viewPager.currentItem = 1
        } else if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Email or Password cannot be empty", Toast.LENGTH_SHORT).show()
        } else if (this.email == email && this.password == password) {
            Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, DashboardActivity::class.java).apply {
                putExtra("fullname", fullname)
                putExtra("nim", nim)
            })
        } else {
            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
        }
    }
}