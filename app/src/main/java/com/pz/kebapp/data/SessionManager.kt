package com.pz.kebapp.data

import android.content.Context
import android.content.SharedPreferences
import com.pz.kebapp.R

class SessionManager(context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(
        context.getString(R.string.app_name), Context.MODE_PRIVATE
    )

    companion object {
        const val USER_TOKEN = "user_token"
    }

    fun saveAuthToken(token: String) {
        val currentTime = System.currentTimeMillis()
        val expirationTime = currentTime + 3600000
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.putLong("$USER_TOKEN.expiration", expirationTime)
        editor.apply()
    }

    fun fetchAuthToken(): String? {
        val currentTime = System.currentTimeMillis()
        val expirationTime = prefs.getLong("$USER_TOKEN.expiration", -1)
        return if (expirationTime <= currentTime) {
            removeAuthToken()
            null
        } else {
            prefs.getString(USER_TOKEN, null)
        }
    }

    fun removeAuthToken() {
        val editor = prefs.edit()
        editor.remove(USER_TOKEN)
        editor.remove("$USER_TOKEN.expiration")
        editor.apply()
    }
}