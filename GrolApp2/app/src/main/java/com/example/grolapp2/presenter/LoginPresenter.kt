package com.example.grolapp2.presenter

import com.example.grolapp2.model.Users
import com.example.grolapp2.repository.UsersRepository
import com.example.grolapp2.view.login.LoginView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginPresenter(private val repository: UsersRepository, private val view : LoginView){
    fun loginUsers(users: Users) = GlobalScope.launch(Dispatchers.Main) {
        repository.loginUsers(users){
            if (it!= null){
                view.loginResult(true)
                view.showMessage("Uspješno ste prijavljeni")
                view.showUsers(it)
            }else{
                view.loginResult(false)
                view.showMessage("Neuspješna prijava")
            }
        }
    }
}