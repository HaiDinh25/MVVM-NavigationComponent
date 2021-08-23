package com.haidv.userlisttest.user.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.haidv.userlisttest.user.database.dao.UserDao
import com.haidv.userlisttest.user.data.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao

    companion object {
        @Volatile
        private var instance: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context, UserDatabase::class.java, "UserDatabase").build()
            }
            return instance!!
        }
    }
}