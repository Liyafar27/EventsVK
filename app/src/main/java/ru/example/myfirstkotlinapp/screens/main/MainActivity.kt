package ru.example.myfirstkotlinapp.screens.main

import android.content.Context
import android.content.SharedPreferences
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.edit
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.omegar.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.example.myfirstkotlinapp.R
import ru.example.myfirstkotlinapp.getApi.getApi
import ru.example.myfirstkotlinapp.getApi.getApi.getipFromaws
import ru.example.myfirstkotlinapp.model.GitHubRepo
import ru.example.myfirstkotlinapp.notification.LimitWorker
import ru.example.myfirstkotlinapp.notification.WorkerStars
import ru.example.myfirstkotlinapp.screens.base.BaseActivity
import ru.example.myfirstkotlinapp.screens.chart.ChartActivity.Companion.createIntent
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.Inet4Address
import java.net.InetAddress
import java.net.NetworkInterface
import java.net.URL
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.util.concurrent.TimeUnit

class MainActivity : BaseActivity(), View.OnClickListener, MainView {
    private lateinit var sharedPreference: SharedPreferences
    var i:String = ""


    @InjectPresenter
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreference = getSharedPreferences("settings", Context.MODE_PRIVATE)
        setAlarm()
        setLimitWorker()
        gl()




        next.setOnClickListener(this)
        prev.setOnClickListener(this)
        buttonSearch.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {

            R.id.prev -> {
                presenter.requestChangePage(-1)
                apipi()
                apipipi2()

                val textView3: TextView = findViewById(R.id.api3)
                textView3.text = i
                }
            R.id.next -> {presenter.requestChangePage(+1)
                startTimeCounter() }
            R.id.buttonSearch -> {


                presenter.requestShowRepo(editText.text.toString())

            }
        }
    }
    fun gl() {
        GlobalScope.launch(Dispatchers.IO) { i = getipFromaws()
        }
    }

    override fun setPageNumber(page: Int) {
        pageNumber.text = getString(R.string.numberPage1, page)
    }

    override fun setRepoName(repoName: String) {
        editText.setText(repoName)
    }

    override fun setList(list: List<GitHubRepo>) {
        val adapterM = RecyclerAdapterMain(list, ::personItemClicked)
        recyclerView.adapter = adapterM
    }

//    override fun setLimit(limit: Limit) {
//        Log.i("Limit", limit.resources.core.remaining.toString())
//    }

    override fun showToast(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
    }

    override fun personItemClicked(person: GitHubRepo) {

        val intent = createIntent(this@MainActivity, person)
        startActivity(intent)
    }
    private fun apipi(){
        val textView: TextView = findViewById(R.id.api)
//        val wifiManager = this@MainActivity.getSystemService(Context.WIFI_SERVICE) as WifiManager
//        val ipAddress: String = Formatter.formatIpAddress(wifiManager.connectionInfo.ipAddress)
        textView.text = getApi.apipipi(this@MainActivity)
    }
    private fun apipipi2(){
//        val whatismyip = URL("http://myexternalip.com/raw")
//        val input = BufferedReader(
//            InputStreamReader(
//                whatismyip.openStream()
//            )
//        )
//
//        val ip: String = input.readLine() //you get the IP as a String
        val textView2: TextView = findViewById(R.id.api2)
        textView2.text = getApi.getIpv4HostAddress()

    }
    fun startTimeCounter() {
        var counter= 0
        val timer = object : CountDownTimer(20000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                counter++
                Log.i("Counter", "$counter")
            }

            override fun onFinish() {
                Log.i("Counter", "Finished")
            }
        }
        timer.start()
    }


    private fun setAlarm() {

        val isFirst = sharedPreference.getBoolean("isFirstLaunch", true);
        if(isFirst){
        val workerStars: PeriodicWorkRequest = PeriodicWorkRequest.Builder(WorkerStars::class.java,
            900000, TimeUnit.MILLISECONDS, 300000,TimeUnit.MILLISECONDS)
            .build()
        WorkManager.getInstance(this)
            .enqueue(workerStars)

            val workerLimit: PeriodicWorkRequest = PeriodicWorkRequest.Builder(LimitWorker::class.java,
                900000, TimeUnit.MILLISECONDS, 300000,TimeUnit.MILLISECONDS)
                .build()
            WorkManager.getInstance(this)
                .enqueue(workerLimit)
        }

//        val intent = Intent(this, AlarmReceiver::class.java)
//        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
//        val manager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
//        manager.setRepeating(
//            AlarmManager.ELAPSED_REALTIME,
//            5000,
//           60000,
//            pendingIntent
//        )
//
        sharedPreference.edit {
            putBoolean("isFirstLaunch", false);
        }
          }
        }
private fun setLimitWorker() {
  }









