package com.example.grolapp2.view.login

import com.example.grolapp2.model.Users

interface LoginView {
    fun showUsers(users: Users)
    fun loginResult(status : Boolean)
    fun showMessage(data : String)
}