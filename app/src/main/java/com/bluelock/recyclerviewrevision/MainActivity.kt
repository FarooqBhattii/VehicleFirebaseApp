package com.bluelock.recyclerviewrevision

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bluelock.recyclerviewrevision.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainUpload.setOnClickListener {
            val intent = Intent(this,UploadActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.mainSearch.setOnClickListener {
            val intent = Intent(this,FetchData::class.java)
            startActivity(intent)
            finish()
        }

        }


    }
