package com.example.grolapp2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")
data class Users(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = 0,
    @ColumnInfo(name = "username")
    var username: String? = "",
    @ColumnInfo(name = "password")
    var password: String? = ""
)