package com.example.grolapp2.repository

import com.example.grolapp2.database.UsersDao
import com.example.grolapp2.model.Users

class UsersRepository(private val  usersDao: UsersDao) {
    suspend fun registerUsers(users: Users){
        usersDao.register(users)
    }

    suspend fun loginUsers(users: Users, onLogin : (Users)->Unit) {
        val result = usersDao.login(users.username, users.password)
        onLogin(result)
    }
}