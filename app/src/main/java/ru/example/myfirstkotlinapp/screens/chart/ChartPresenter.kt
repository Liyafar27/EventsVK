package ru.example.myfirstkotlinapp.screens.chart

import android.content.Context
import androidx.core.content.ContextCompat.getColor
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.omegar.mvp.InjectViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.example.myfirstkotlinapp.R.color.amber
import ru.example.myfirstkotlinapp.screens.base.BasePresenter
import ru.example.myfirstkotlinapp.model.Stargazer
import ru.example.myfirstkotlinapp.data.storage.Storage
import ru.example.myfirstkotlinapp.model.User
import ru.example.myfirstkotlinapp.utils.*
import java.util.*

@InjectViewState

class ChartPresenter(private var repoRecycle: String, private var ownerRecycle: String) :
    BasePresenter<ChartView>() {
    private val  storage = Storage.instance

    var year: Int = Date().getYearInt()
        set(value) {
            field = value
            viewState.setDateYear(value)
            requestDataForYear()
        }
    private val perPage: Int = 100

    private var page: Int = 1


    init {
        viewState.setDateYear(year)
    }

    fun requestDataForYear() {
        GlobalScope.launch(Dispatchers.Main) {
            val list = storage.getStorageStargazers(repoRecycle, ownerRecycle, page, perPage)
            viewState.setList(list, year)}

    }

    fun requestChangeYear(diff: Int) {
        year += diff
    }

    fun createValuesForChart(repo: List<Stargazer>, yearInt: Int): List<Float> {

        val dateOfStars = repo.map { it.starred_at.removeTime() }

        val dateOfStarsGrouping = dateOfStars
            .groupingBy { it }
            .eachCount()

        val dateOfStarsYear = dateOfStarsGrouping.filterYearForDate(yearInt)

        return certainMonthForDate(dateOfStarsYear)
    }

    fun createData(repo: List<Stargazer>, yearInt: Int): Map<User, Date> {

        val userList = repo.map { it.starred_at.removeTime() }
        val users = repo.map { it.user }

        val mapIntent = users
            .mapIndexed { i, user -> user to userList[i] }
            .toMap()

        return mapIntent.filterYearForUserDate(yearInt)
    }

    fun setDataForBarChart(barGroup: List<BarEntry>, barChart: BarChart, context: Context) {

        val barDataSet = BarDataSet(barGroup, "").also {
            it.color = getColor(context, amber)
        }


        barChart.data = BarData(barDataSet).apply {
            setDrawValues(false)
        }

        barChart.animateY(700)
    }
}



