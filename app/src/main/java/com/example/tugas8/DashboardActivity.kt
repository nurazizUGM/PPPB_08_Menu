package com.example.tugas8

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tugas8.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {
    private val binding by lazy { ActivityDashboardBinding.inflate(layoutInflater) }
    private var fullname = ""
    private var email = ""
    private var nim = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        this.fullname = intent.getStringExtra("fullname").toString()
        this.email = intent.getStringExtra("email").toString()
        this.nim = intent.getStringExtra("nim").toString()
        binding.tvWelcome.text = "Welcome, $fullname"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.dashboard_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_profile -> {
                startActivity(Intent(this, ProfileActivity::class.java).apply {
                    putExtra("fullname", fullname)
                    putExtra("email", email)
                    putExtra("nim", nim)
                })
                return true
            }

            R.id.action_logout -> {
                finish()
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}