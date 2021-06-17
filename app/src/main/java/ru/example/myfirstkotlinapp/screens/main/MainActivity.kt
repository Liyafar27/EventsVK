package ru.example.myfirstkotlinapp.screens.main

import android.app.DatePickerDialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.omegar.mvp.presenter.InjectPresenter
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKScope
import kotlinx.android.synthetic.main.activity_main.*
import ru.example.myfirstkotlinapp.R
import ru.example.myfirstkotlinapp.data.remote.RemoteItemEvent
import ru.example.myfirstkotlinapp.screens.base.BaseActivity
import ru.example.myfirstkotlinapp.screens.eventInfo.EventInfoActivity.Companion.createIntentEvent
import ru.example.myfirstkotlinapp.utils.convertLongToTime
import java.util.*

class MainActivity : BaseActivity(), View.OnClickListener, MainView {
    private lateinit var sharedPreference: SharedPreferences

    private val cStart = Calendar.getInstance()
    private val yearStart = cStart.get(Calendar.YEAR)
    private val monthStart = cStart.get(Calendar.MONTH)
    private val dayStart = cStart.get(Calendar.DAY_OF_MONTH)

    private val cEnd = Calendar.getInstance()
    private val yearEnd = cEnd.get(Calendar.YEAR)
    private val monthEnd = cEnd.get(Calendar.MONTH)
    private val dayEnd = cEnd.get(Calendar.DAY_OF_MONTH)

    @InjectPresenter
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        VK.login(this, arrayListOf(VKScope.FRIENDS, VKScope.PHOTOS))

        val buttonDatePickerStart: Button = findViewById(R.id.button_calendar_start)
        val buttonDatePickerEnd: Button = findViewById(R.id.button_calendar_end)

        buttonDatePickerStart.setOnClickListener(this)
        buttonDatePickerEnd.setOnClickListener(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter.loginVk(requestCode = requestCode, resultCode = resultCode, data = data)
        presenter.updateRepo()

    }

    override fun onClick(view: View) {
        when (view.id) {

            R.id.button_calendar_start -> {
                pickerStart()
                presenter.requestStartDate(button_calendar_start.text.toString())
            }
            R.id.button_calendar_end -> {
                pickerEnd()
                presenter.requestEndDate(button_calendar_end.text.toString())
            }
        }
    }


    override fun setDateStart(dateStart: String) {
    }

    override fun setDateEnd(dateEnd: String) {
    }

    override fun showToast(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
    }

    private fun pickerStart() {

        val dpdStart = DatePickerDialog(
            this@MainActivity,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                cStart.set(year, monthOfYear, dayOfMonth)
                val textDay = "" + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year
                button_calendar_start.setText(textDay)
                presenter.requestStartDate(button_calendar_start.text.toString())
            },
            yearStart,
            monthStart,
            dayStart
        )
        dpdStart.datePicker.setMinDate(System.currentTimeMillis() - 1000)
        dpdStart.show()

    }

    private fun pickerEnd() {

        val dpdEnd = DatePickerDialog(
            this@MainActivity,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                cEnd.set(year, monthOfYear, dayOfMonth)
                val texte = "" + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year
                button_calendar_end.setText(texte)
                presenter.requestEndDate(button_calendar_end.text.toString())

            },
            yearEnd,
            monthEnd,
            dayEnd
        )
        dpdEnd.datePicker.setMinDate(System.currentTimeMillis() - 1000)
        dpdEnd.show()

    }


    override fun showError(message: String) {
        Toast.makeText(applicationContext, "textResource", Toast.LENGTH_SHORT).show()
    }

    override fun personItemClicked(person: RemoteItemEvent) {
        var imageUrlIntent: String = ""
        val nameIntent = person.name
        val startDateIntent = person.start_date.toString()
        val endDateIntent = person.finish_date.toString()
        imageUrlIntent = if (!person.cover?.images?.get(2)?.toString().isNullOrEmpty()) {
            person.cover?.images?.get(2).toString()
        } else {
            ""
        }

        val descriptionIntent = person.description

        val intent = createIntentEvent(
            this@MainActivity,
            nameIntent,
            startDateIntent,
            endDateIntent,
            imageUrlIntent,
            descriptionIntent
        )
        startActivity(intent)
    }

    override fun setList(list: List<RemoteItemEvent>) {
        var listFilter = list
        val filterStartDay = cStart.time
        val filterEndDay = cEnd.time
        lateinit var adapterM: RecyclerAdapterMain

        val startDate = button_calendar_start.text.toString().isEmpty()
        val endDate = button_calendar_end.text.toString().isEmpty()
        Log.i("endDate", button_calendar_end.text.toString())

        if (startDate && endDate) {
            adapterM = RecyclerAdapterMain(list, ::personItemClicked)

        } else if (!startDate && endDate) {
            listFilter =
                listFilter.filter { convertLongToTime(it.start_date * 1000).after(filterStartDay) }
            adapterM = RecyclerAdapterMain(listFilter, ::personItemClicked)

        } else if (startDate && !endDate) {

            listFilter =
                listFilter.filter { convertLongToTime(it.start_date * 1000).before(filterEndDay) }
            listFilter =
                listFilter.filter { convertLongToTime(it.finish_date * 1000).before(filterEndDay) }
            adapterM = RecyclerAdapterMain(listFilter, ::personItemClicked)

        } else if (!startDate && !endDate) {
            listFilter =
                listFilter.filter { convertLongToTime(it.start_date * 1000).after(filterStartDay) }
            listFilter =
                listFilter.filter { convertLongToTime(it.finish_date * 1000).before(filterEndDay) }
            adapterM = RecyclerAdapterMain(listFilter, ::personItemClicked)
        }

        recyclerView.adapter = adapterM
    }


    override fun startLoading() {
    }

    override fun endLoading() {
    }

    override fun openFriends() {
    }
}










