package ru.example.myfirstkotlinapp

import android.app.Application
import com.vk.api.sdk.VK

class StarsApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        VK.initialize(applicationContext)
    }
}

