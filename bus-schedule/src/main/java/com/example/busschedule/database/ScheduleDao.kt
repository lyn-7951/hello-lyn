package com.example.busschedule.database

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/*@Dao使该接口用于Room*/
@Dao
interface ScheduleDao {
//    按公交到站时间依升序
    @Query("SELECT * FROM schedule ORDER BY arrival_time ASC")
    fun getAll(): Flow<List<Schedule>>

//:stopName 引用查询中的kotlin值
    @Query("SELECT * FROM schedule WHERE stop_name = :stopName ORDER BY arrival_time ASC")
   fun getByStopName(stopName: String): Flow<List<Schedule>>
}