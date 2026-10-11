package com.example.languagelearner

import android.content.Context

class WordRepository(private val context: Context) {

    fun loadWordsFromFile(fileResource: Int): List<String> {
        val words = mutableListOf<String>()

        val inputStream = context.resources.openRawResource(fileResource)
        inputStream.bufferedReader().useLines { lines ->
            lines.forEach { line ->
                words.add(line)
            }
        }

        return words
    }
}