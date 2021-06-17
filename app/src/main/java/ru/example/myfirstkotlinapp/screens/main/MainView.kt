package ru.example.myfirstkotlinapp.screens.main


import com.omegar.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.omegar.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.omegar.mvp.viewstate.strategy.StateStrategyType
import ru.example.myfirstkotlinapp.data.remote.RemoteItemEvent
import ru.example.myfirstkotlinapp.screens.base.BaseView

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView: BaseView {
    fun setDateStart(dateStart: String)
    fun setDateEnd(dateEnd: String)
    fun personItemClicked(person: RemoteItemEvent)
    fun setList(list: List<RemoteItemEvent>)
    fun startLoading()
    fun endLoading()
    fun openFriends()
    fun showError(message:String)
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showToast(message: String)

}


