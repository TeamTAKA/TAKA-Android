package com.taka.taka.presentation.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddViewModel: ViewModel() {

    private val _state: MutableLiveData<String> = MutableLiveData()
    val state: LiveData<String>
        get() = _state


}