package com.example.grolapp2.presenter

import com.example.grolapp2.model.Users
import com.example.grolapp2.repository.UsersRepository
import com.example.grolapp2.view.register.RegisterView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterPresenter(private val repository: UsersRepository, private val view: RegisterView) {
    fun registerUsers(users: Users) = GlobalScope.launch(Dispatchers.Main) {
        repository.registerUsers(users)
        view.showRegister(true)
        view.showMessage("Uspješno ste registrirani!")
    }
}