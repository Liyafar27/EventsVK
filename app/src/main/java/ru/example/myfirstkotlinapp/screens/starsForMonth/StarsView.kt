package ru.example.myfirstkotlinapp.screens.starsForMonth


import com.omegar.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.omegar.mvp.viewstate.strategy.StateStrategyType
import ru.example.myfirstkotlinapp.screens.base.BaseView


@StateStrategyType(AddToEndSingleStrategy::class)
interface StarsView : BaseView {
    fun showMonth(currentMonth: String, currentYear: String)
    fun showStarsListForMonth(urlIntent: ArrayList<String>, loginIntent: ArrayList<String>)
}