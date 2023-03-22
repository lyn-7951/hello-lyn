package com.example.busschedule.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/*数据库类*/
/*//所有实体类型（访问类型本身需要使用 ClassName::class）都列于一个数组中。
    * version数据库版本号*/
@Database(
    entities = [Schedule::class],
    version = 1
)
abstract  class AppDatabase : RoomDatabase() {


    /*使用 AppDatabase 类时，您需要确保仅存在一个数据库实例*/
    companion object {
//        @Volatile用于 AppDatabase 实例以避免可能出现的 bug。
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database")
                    .createFromAsset("database/bus_schedule.db")//加载现有数据库
                    .build()
                INSTANCE = instance

                instance
            }
        }



    }
    abstract fun scheduleDao(): ScheduleDao

}