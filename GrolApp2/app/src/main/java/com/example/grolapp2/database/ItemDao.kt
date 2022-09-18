package com.example.grolapp2.database

import androidx.room.*
import com.example.grolapp2.model.Items

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(item: Items)

    @Query("SELECT * FROM items_table WHERE item_category = :category")
    suspend fun getAll(category: String) : List<Items>

    @Delete
    suspend fun deleteItem(item: Items)
}