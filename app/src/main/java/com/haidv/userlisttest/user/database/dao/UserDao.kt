package com.haidv.userlisttest.user.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.haidv.userlisttest.user.data.User

@Dao
interface UserDao {
    @Insert
    suspend fun insertAllUser (users: List<User>)

    @Query("DELETE FROM userTable")
    suspend fun deleteAllUser ()

    @Query("SELECT * FROM userTable")
    fun getAllUser(): LiveData<List<User>>

}