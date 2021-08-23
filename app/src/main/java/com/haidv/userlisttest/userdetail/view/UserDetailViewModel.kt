package com.haidv.userlisttest.userdetail.view

import android.app.Application
import androidx.lifecycle.*
import com.haidv.userlisttest.userdetail.data.UserDetailRepository
import com.haidv.userlisttest.utils.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import java.lang.IllegalArgumentException

class UserDetailViewModel(private var application: Application, private val id: Int) : ViewModel() {

    private val userDetailRepository: UserDetailRepository = UserDetailRepository(id)

    fun getUserDetailFromAPI() = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(userDetailRepository.getUserDetailFromApi()))
        } catch (ex: Exception) {
            emit(Resource.error(null, ex.message ?: "Error!"))
        }
    }

    class UserDetailViewModelFactory(private val application: Application, private val id: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            if (modelClass.isAssignableFrom(UserDetailViewModel::class.java)) {
                return UserDetailViewModel(application, id) as T
            }
            throw IllegalArgumentException("Unable construct viewModel")
        }
    }

}