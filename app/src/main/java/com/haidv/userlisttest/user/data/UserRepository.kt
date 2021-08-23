package com.haidv.userlisttest.user.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.haidv.userlisttest.services.APIConfig
import com.haidv.userlisttest.user.database.UserDatabase
import com.haidv.userlisttest.user.database.dao.UserDao

class UserRepository(application: Application) {

    private val userDao: UserDao

    init {
        val userDatabase: UserDatabase = UserDatabase.getInstance(application)
        userDao = userDatabase.getUserDao()
    }

    suspend fun insertAllUserToDatabase(userList: List<User>) = userDao.insertAllUser(userList)

    suspend fun deleteAllUserToDatabase() = userDao.deleteAllUser()

    fun getAllUser():LiveData<List<User>> = userDao.getAllUser()

    suspend fun getUserFromApi() = APIConfig.apiService.getUser()

}