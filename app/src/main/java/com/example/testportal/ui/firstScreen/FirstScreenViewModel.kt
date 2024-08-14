package com.example.testportal.ui.firstScreen

import androidx.lifecycle.ViewModel

class FirstScreenViewModel: ViewModel() {

    fun checkPalindrome(input: String): Boolean{
        val cleanedInput = input.replace(Regex("[^A-Za-z0-9]"), "").lowercase()
        return cleanedInput == cleanedInput.reversed()
    }
}