package com.kishorejethava.moviepilot

import android.app.Application
import com.kishorejethava.moviepilot.webservice.ApiService
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MoviePilotApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val networkModule = module {
//            single { ApiService() }
        }
        startKoin {
            androidContext(this@MoviePilotApplication)
            listOf(networkModule)
        }
    }

}