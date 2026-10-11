package com.example.languagelearner

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WordViewModel : ViewModel() {
    private val _currentSlovakWord = MutableLiveData<String>()
    private val _currentForeignWord = MutableLiveData<String>()
    val currentSlovakWord: LiveData<String> get() = _currentSlovakWord
    val currentForeignWord: LiveData<String> get() = _currentForeignWord

    private var slovakWords: List<String> = emptyList()
    private var foreignWords: List<String> = emptyList()
    private var index = 0

    fun setWords(slovak: List<String>, foreign: List<String>) {
        index = if (slovak.isNotEmpty() && index >= slovak.size) slovak.size - 1 else index

        slovakWords = if (slovak.isEmpty()) listOf("No Slovak words available") else slovak
        foreignWords = if (foreign.isEmpty()) listOf("No foreign words available") else foreign

        updateWords()
    }

    fun nextWord() {
        if (slovakWords.isNotEmpty()) {
            index = (index + 1) % slovakWords.size
            updateWords()
        }
    }

    private fun updateWords() {
        _currentSlovakWord.value = slovakWords.getOrNull(index) ?: "No Slovak words available"
        _currentForeignWord.value = foreignWords.getOrNull(index) ?: "No foreign words available"
    }
}