package ru.example.myfirstkotlinapp.screens.base

import android.os.Bundle
import com.omegar.mvp.MvpAppCompatActivity
import com.vk.api.sdk.VK


abstract class BaseActivity : MvpAppCompatActivity(), BaseView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        VK.initialize(this);

    }

}