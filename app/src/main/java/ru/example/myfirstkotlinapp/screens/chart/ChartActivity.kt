package ru.example.myfirstkotlinapp.screens.chart

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.omegar.mvp.presenter.InjectPresenter
import com.omegar.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_chart.*
import ru.example.myfirstkotlinapp.screens.base.BaseActivity
import ru.example.myfirstkotlinapp.R
import ru.example.myfirstkotlinapp.model.GitHubRepo
import ru.example.myfirstkotlinapp.model.Stargazer
import ru.example.myfirstkotlinapp.model.User
import ru.example.myfirstkotlinapp.screens.starsForMonth.StarsForMonthActivity.Companion.createIntents
import ru.example.myfirstkotlinapp.utils.*
import java.util.*


class ChartActivity : BaseActivity(), View.OnClickListener, ChartView {
    var data: Map<User, Date> = emptyMap()
     @InjectPresenter
    lateinit var presenter: ChartPresenter

    companion object {

        private const val EXTRA_PERSON_NAME = "personName"
        private const val EXTRA_REPO_NAME = "repoName"

        fun createIntent(context: Context, person: GitHubRepo): Intent {
            return Intent(context, ChartActivity::class.java)
                .putExtra(EXTRA_PERSON_NAME, person.owner.login)
                .putExtra(EXTRA_REPO_NAME, person.name)
        }
    }

    @ProvidePresenter
    fun providePresenter(): ChartPresenter {
        val repoRecycle = intent.getStringExtra(EXTRA_REPO_NAME)!!
        val ownerRecycle = intent.getStringExtra(EXTRA_PERSON_NAME)!!

        return ChartPresenter(repoRecycle, ownerRecycle)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)


        providePresenter()

        nextRepo.setOnClickListener(this)
        prevRepo.setOnClickListener(this)

        setupBarChart(presenter.year)
        presenter.requestDataForYear()
    }

//    fun setInfoForNotification(){
//        val repoRecycle = intent.getStringExtra(EXTRA_REPO_NAME)!!
//        val  storage = Storage.instance
//        val page =1
//        val PER_PAGE=100
//        GlobalScope.launch(Dispatchers.IO) {
//
//            var message = storage.getStorageForNotification(repoRecycle, page, PER_PAGE)
//            message += message
//            AppService.startServiceSecond(this@ChartActivity,  message ) }
//    }

    override fun setDateYear(year: Int) {
        pageNumber.text = getString(R.string.yearStr1, year)
    }

    override fun setupBarChart(year: Int) {
        barChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        barChart.xAxis.labelCount = 11
        barChart.xAxis.valueFormatter = IndexAxisValueFormatter(MonthObj.month)
        barChart.xAxis.enableGridDashedLine(5f, 5f, 0f)
        barChart.axisRight.enableGridDashedLine(5f, 5f, 0f)
        barChart.axisLeft.enableGridDashedLine(5f, 5f, 0f)
        barChart.description.isEnabled = false
        barChart.legend.isEnabled = false
        barChart.setPinchZoom(true)

        barChart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {

            override fun onValueSelected(e: Entry, h: Highlight?) {

                val entryX = e.x.toInt()
                val stringForIntent = certainMonthForUserDate(data, entryX)
                val avatarsUrl = stringForIntent.keys.map { user -> user.avatar_url }
                val loginList = stringForIntent.keys.map { user -> user.login }
                val month = MonthObj.month[entryX]
//                setInfoForNotification()

                val intent = createIntents(this@ChartActivity, loginList, avatarsUrl, year, month)
                startActivity(intent)
            }

            override fun onNothingSelected() {

            }
        })
    }

    override fun setList(list: List<Stargazer>, year: Int) {
        val listForChart = presenter.createValuesForChart(list, year)
        data = presenter.createData(list, year)
        presenter.setDataForBarChart(listForChart.mapBarEntry(), barChart,this@ChartActivity)
    }

    override fun showToast(message: String) {
        Toast.makeText(this@ChartActivity, message, Toast.LENGTH_SHORT).show()
    }


    override fun onClick(view: View) {
        when (view.id) {
            R.id.prevRepo -> presenter.requestChangeYear(-1)
            R.id.nextRepo -> presenter.requestChangeYear(+1)
        }
        presenter.requestDataForYear()
    }

    private fun List<Float>.mapBarEntry() = mapIndexed { i, fl ->
        BarEntry(i.toFloat(), fl, MonthObj.month[i])
    }

}