package com.eligon.widget

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eligon.widget.repository.Repository
import com.google.gson.JsonArray
import kotlinx.coroutines.launch


class MainViewModel(private val repository: Repository): ViewModel() {

    val myResponse: MutableLiveData<List<JsonArray>> = MutableLiveData()

    fun getMetals() {
        viewModelScope.launch {
            val goldJsonArray = repository.getGold()
            val silverJsonArray = repository.getSilver()
            val platinumJsonArray = repository.getPlatinum()
            val palladiumJsonArray = repository.getPalladium()

            val response = listOf(
                goldJsonArray,
                silverJsonArray,
                platinumJsonArray,
                palladiumJsonArray)
            myResponse.value = response
        }
    }
}