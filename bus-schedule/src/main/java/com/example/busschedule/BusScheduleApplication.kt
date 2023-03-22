package com.example.busschedule

import android.app.Application
import com.example.busschedule.database.AppDatabase

class BusScheduleApplication : Application() {
    //lazy延迟属性
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}