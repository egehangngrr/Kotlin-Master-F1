package com.ozzy.kukaf1.app

import android.app.Application
import android.content.Context
import com.ozzy.kukaf1.service.UtilsModule

class MyApplication : Application() {
    lateinit  var appComponent: AppComponent
        internal set
    lateinit var context: Context

    override fun onCreate() {
        super.onCreate()
        context = this
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).utilsModule(UtilsModule()).build()
    }
}