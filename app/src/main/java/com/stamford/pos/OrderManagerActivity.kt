package com.stamford.pos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.stamford.pos.databinding.ActivityOrderManagerBinding

class OrderManagerActivity: AppCompatActivity() {
    private val TAG = "OrderManagerActivity"
    private lateinit var binding: ActivityOrderManagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}