package com.example.testportal.ui.thirdScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.testportal.Repository
import com.example.testportal.network.response.DataItem

class ThirdScreenViewModel(private val repository: Repository): ViewModel(){

    val apiResponse : LiveData<PagingData<DataItem>> = repository.getListUser().cachedIn(viewModelScope)

}