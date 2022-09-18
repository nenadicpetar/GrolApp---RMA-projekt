package com.example.grolapp2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items_table")
data class Items (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "item_category")
    val category: String,

    @ColumnInfo(name = "item_name")
    val name: String,

    @ColumnInfo(name = "number_of_items")
    val number: String
)
