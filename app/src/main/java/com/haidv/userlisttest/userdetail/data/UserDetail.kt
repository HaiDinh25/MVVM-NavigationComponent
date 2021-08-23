package com.haidv.userlisttest.userdetail.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "userDetailTable")
class UserDetail : Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_detail_login_col")
    var login: Int = 0

    @ColumnInfo(name = "user_detail_name_col")
    var name: String = ""

    @ColumnInfo(name = "user_detail_avatar_url_col")
    var avatar_url: String = ""

    @ColumnInfo(name = "user_detail_location_col")
    var location: String = ""

    @ColumnInfo(name = "user_detail_followers_col")
    var followers: Int = 0

    @ColumnInfo(name = "user_detail_following_col")
    var following: Int = 0

    @ColumnInfo(name = "user_detail_public_repos_col")
    var public_repos: Int = 0

    @ColumnInfo(name = "user_detail_bio_col")
    var bio: String = ""

}