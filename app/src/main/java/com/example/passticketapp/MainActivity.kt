package com.example.passticketapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.passticketapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TicketApp.currentActivity = this

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}