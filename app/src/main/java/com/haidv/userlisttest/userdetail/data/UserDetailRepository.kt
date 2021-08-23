package com.haidv.userlisttest.userdetail.data

import com.haidv.userlisttest.services.APIConfig

class UserDetailRepository(private var id: Int) {

    suspend fun getUserDetailFromApi() = APIConfig.apiService.getUserDetail(id)

}