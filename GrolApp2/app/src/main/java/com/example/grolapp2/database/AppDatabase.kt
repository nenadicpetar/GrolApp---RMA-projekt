package com.example.grolapp2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.grolapp2.model.Items
import com.example.grolapp2.model.Users


@Database(entities = [Users::class, Items::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun usersDao() : UsersDao
    abstract fun itemsDao(): ItemDao

    companion object{
        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getDatabase(context: Context) : AppDatabase{
            val tempInstance = INSTANCE
            if (tempInstance!=null) {
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}