package ru.example.myfirstkotlinapp.screens.base

import android.os.Bundle
import com.omegar.mvp.MvpAppCompatActivity


abstract class BaseActivity : MvpAppCompatActivity(), BaseView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}