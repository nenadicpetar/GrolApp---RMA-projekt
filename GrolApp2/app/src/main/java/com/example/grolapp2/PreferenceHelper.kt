package com.example.grolapp2

import android.content.Context

class PreferenceHelper(private val context: Context){
    companion object{
        private const val LOGIN = "LOGIN"
        private const val USERNAME = "USERNAME"
    }

    private val sharedPreferences = context.getSharedPreferences("demo", Context.MODE_PRIVATE)

    var login : Boolean
        get() {
            return sharedPreferences.getBoolean(LOGIN, false)
        }
        set(value) {
            sharedPreferences.edit().apply(){
                putBoolean(LOGIN, value)
                apply()
            }
        }

    var username : String?
        get() {
            return sharedPreferences.getString(USERNAME, null)
        }
        set(value) {
            sharedPreferences.edit().apply(){
                putString(USERNAME,value)
                apply()
            }
        }
}