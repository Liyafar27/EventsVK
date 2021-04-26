package ru.example.myfirstkotlinapp

import android.app.Application
//import ru.example.myfirstkotlinapp.dagger.AppComponent
//import ru.example.myfirstkotlinapp.dagger.AppModule
//import ru.example.myfirstkotlinapp.dagger.GitApplication
import ru.example.myfirstkotlinapp.room.RepoDatabase

class StarsApplication : Application() {
//    lateinit var gitComponent: AppComponent


    override fun onCreate() {
        super.onCreate()
        RepoDatabase.initDatabase(this)
//        gitComponent = initDagger(this)
    }


//    private fun initDagger(app: StarsApplication): AppComponent =
//        DaggerAppComponent.builder()
//            .appModule(AppModule(app))
//            .build()
}

