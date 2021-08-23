package com.haidv.userlisttest.user.view.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.haidv.userlisttest.user.data.UserRepository
import com.haidv.userlisttest.user.data.User
import com.haidv.userlisttest.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import java.lang.IllegalArgumentException

class UserViewModel(private var application: Application) : ViewModel() {

    private val userRepository: UserRepository = UserRepository(application)

    fun insertAllUserToDatabase(user: List<User>) = viewModelScope.launch {
        userRepository.insertAllUserToDatabase(user)
    }

    fun deleteAllUserToDatabase() = viewModelScope.launch {
        userRepository.deleteAllUserToDatabase()
    }

    fun getAllUser(): LiveData<List<User>> = userRepository.getAllUser()

    fun getUserFromAPI() = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(userRepository.getUserFromApi()))
        } catch (ex: Exception) {
            emit(Resource.error(null, ex.message ?: "Error!"))
        }
    }

    class UserViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
                return UserViewModel(application) as T
            }
            throw IllegalArgumentException("Unable construct viewModel")
        }

    }

}