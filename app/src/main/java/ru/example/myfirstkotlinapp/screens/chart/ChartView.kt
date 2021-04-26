package ru.example.myfirstkotlinapp.screens.chart

import com.omegar.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.omegar.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.omegar.mvp.viewstate.strategy.StateStrategyType
import ru.example.myfirstkotlinapp.model.Stargazer
import ru.example.myfirstkotlinapp.screens.base.BaseView

@StateStrategyType(AddToEndSingleStrategy::class)
interface ChartView: BaseView {
    fun setDateYear(year: Int)
    fun setupBarChart(year: Int)
    fun setList(list: List<Stargazer>, year: Int)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showToast(message: String)
}
