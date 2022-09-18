package com.example.grolapp2.repository

import com.example.grolapp2.database.ItemDao
import com.example.grolapp2.model.Items

class ItemsRepository(private val itemDao: ItemDao) {
    suspend fun save(item: Items) {
        itemDao.save(item)
    }

    suspend fun get(category: String) =
        itemDao.getAll(category)

}