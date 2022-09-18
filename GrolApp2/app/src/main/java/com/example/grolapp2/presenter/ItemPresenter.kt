package com.example.grolapp2.presenter

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.grolapp2.model.Items
import com.example.grolapp2.model.Users
import com.example.grolapp2.repository.ItemsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ItemPresenter(private val repository: ItemsRepository) {
    val items = MutableLiveData<List<Items>>()

    fun save(item: Items) = GlobalScope.launch(Dispatchers.Main) {
        repository.save(item)
    }

    fun get(category: String) {
        GlobalScope.launch(Dispatchers.Main) {
            items.value = repository.get(category)
        }
    }
}