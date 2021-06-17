package ru.example.myfirstkotlinapp.screens.base

import com.omegar.mvp.MvpPresenter

open class BasePresenter<View : BaseView> : MvpPresenter<View>() {

}