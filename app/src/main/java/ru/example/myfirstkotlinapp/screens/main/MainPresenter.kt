package ru.example.myfirstkotlinapp.screens.main

import android.content.Intent
import android.os.Handler
import com.omegar.mvp.InjectViewState
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.example.myfirstkotlinapp.data.storage.Storage
import ru.example.myfirstkotlinapp.screens.base.BasePresenter


@InjectViewState
class MainPresenter : BasePresenter<MainView>() {
    val storage = Storage.instance

    var startDates: String = ""
        set(value) {
            field = value
            viewState.setDateStart(field)
            updateRepo()
        }
    var endDates: String = ""
        set(value) {
            field = value
            viewState.setDateEnd(field)
            updateRepo()
        }

    init {
        viewState.setDateStart(startDates)
        viewState.setDateEnd(endDates)

    }

    fun requestStartDate(startDate: String) {
        this.startDates = startDate
    }

    fun requestEndDate(endDate: String) {
        this.endDates = endDate
    }

    fun updateRepo() {
        GlobalScope.launch(Dispatchers.Main) {
            viewState.setList(storage.getRequestEvent().response!!.items)
        }
    }


    fun login(isSuccess: Boolean) {
        viewState.startLoading()
        Handler().postDelayed({

            viewState.endLoading()
            if (isSuccess) {
                viewState.openFriends()
            } else {
                viewState.showError("Упс...Ошибочка вышла")
            }
        }, 500)
    }

    fun loginVk(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
        if (!VK.onActivityResult(
                requestCode = requestCode,
                resultCode = resultCode,
                data = data,
                callback = object : VKAuthCallback {
                    override fun onLogin(token: VKAccessToken) {
                        login(true)
                    }
                    override fun onLoginFailed(errorCode: Int) {
                        viewState.showError("Упс...Ошибочка вышла")
                    }
                })
        ) {
            return false
        }
        return true
    }
}
