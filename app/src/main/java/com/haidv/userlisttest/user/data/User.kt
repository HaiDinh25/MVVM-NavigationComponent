package com.haidv.userlisttest.user.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "userTable")
class User: Serializable {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id_col")
    var id: Int = 0

    @ColumnInfo(name = "user_login_col")
    var login: String = ""

    @ColumnInfo(name = "user_avatar_url_col")
    var avatar_url: String = ""

    @ColumnInfo(name = "user_html_url_col")
    var html_url: String = ""
}