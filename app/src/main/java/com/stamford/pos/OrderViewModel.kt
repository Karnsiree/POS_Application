package com.stamford.pos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OrderViewModel: ViewModel() {
    // Step 1: Create an instance of LiveData to hold a certain
    // type of data. In this case an Int
    val totalAmount: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    // getter
    fun getTotalAmount(): LiveData<Int> {
        return totalAmount
    }
}