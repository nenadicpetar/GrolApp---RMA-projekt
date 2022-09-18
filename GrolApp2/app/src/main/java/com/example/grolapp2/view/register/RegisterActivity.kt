package com.example.grolapp2.view.register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.grolapp2.R
import com.example.grolapp2.database.AppDatabase
import com.example.grolapp2.model.Users
import com.example.grolapp2.presenter.RegisterPresenter
import com.example.grolapp2.repository.UsersRepository
import com.example.grolapp2.view.login.MainActivity
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), RegisterView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val appDatabase = AppDatabase.getDatabase(this)
        val repository = UsersRepository(appDatabase.usersDao())
        val presenter = RegisterPresenter(repository, this)
        btnRegister.setOnClickListener {
            val username = edtUsernameRegister.text.toString()
            val password = edtPasswordRegister.text.toString()
            val users = Users(null, username, password)
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Unesite korisničko ime i lozinku.", Toast.LENGTH_SHORT).show()
            }else{
                presenter.registerUsers(users)
            }
        }
    }

    override fun showRegister(status: Boolean) {
        if (status){
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }
    }

    override fun showMessage(data: String) {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
    }
}