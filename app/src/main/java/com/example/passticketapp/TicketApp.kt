package com.example.passticketapp

import android.app.Application
import android.content.Context
import androidx.fragment.app.FragmentActivity

class TicketApp : Application() {
    companion object {
        private lateinit var instance: TicketApp

        var currentActivity: FragmentActivity? = null

        @JvmStatic
        fun getContext(): Context {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}