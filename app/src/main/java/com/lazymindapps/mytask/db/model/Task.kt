package com.lazymindapps.mytask.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tbl_task")
data class Task (
@PrimaryKey(autoGenerate = true )
var id:Int?,
var task : String?,
var description :String,
var date:Long,
        )