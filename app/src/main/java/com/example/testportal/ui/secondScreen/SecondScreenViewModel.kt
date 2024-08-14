package com.example.testportal.ui.secondScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SecondScreenViewModel: ViewModel() {
    private val _username = MutableLiveData<String?>()
    val username: LiveData<String?> get() = _username

    private val _selectedUser = MutableLiveData<String?>()
    val selectedUser: LiveData<String?> get() = _selectedUser

    fun selectData(data: String?) {
        _selectedUser.postValue(data)
    }

    fun username(data: String?) {
        _username.postValue(data)
    }
}