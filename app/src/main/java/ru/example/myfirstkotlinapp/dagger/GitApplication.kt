//package ru.example.myfirstkotlinapp.dagger
//
//import android.app.Application
//
//class GitApplication:  Application() {
//
//        lateinit var gitComponent: AppComponent
//
//        override fun onCreate() {
//            super.onCreate()
//            gitComponent = initDagger(this)
//        }
//
//        private fun initDagger(app: GitApplication): AppComponent =
//            DaggerAppComponent.builder()
//                .appModule(AppModule(app))
//                .build()
//    }
