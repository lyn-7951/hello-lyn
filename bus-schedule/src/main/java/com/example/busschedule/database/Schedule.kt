package com.example.busschedule.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*数据类*/
/*@Entity数据库定义表；
* Schedule类名称定义表名
* @Entity(tableName="schedule") 指定表名*/
@Entity
data class Schedule (
    //PrimaryKey此注解会告知 Room 在插入新行时将此属性视为主键。
    @PrimaryKey val id: Int ,
    //SQL 列名称使用以下划线分隔的单词,NonNull非空注解
    @NonNull @ColumnInfo(name = "stop_name") val stopName: String,
//
    @NonNull @ColumnInfo(name = "arrival_time") val arrivalTime: Int
)