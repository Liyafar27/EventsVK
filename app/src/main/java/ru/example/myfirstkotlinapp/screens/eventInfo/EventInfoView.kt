package ru.example.myfirstkotlinapp.screens.eventInfo

import com.omegar.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.omegar.mvp.viewstate.strategy.StateStrategyType
import ru.example.myfirstkotlinapp.screens.base.BaseView


@StateStrategyType(AddToEndSingleStrategy::class)
interface EventInfoView : BaseView {
    fun showStarsListForMonth(
        nameIntent: String,
        startDateIntent: String,
        endDateIntent: String,
        imageUrlIntent: String?,
        descriptionIntent: String
    )
}