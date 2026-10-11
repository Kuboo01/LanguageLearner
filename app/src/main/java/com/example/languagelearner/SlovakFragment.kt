package com.example.languagelearner

import android.os.Bundle
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController


class SlovakFragment : Fragment(R.layout.fragment_slovak) {
    private lateinit var wordViewModel: WordViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        wordViewModel = ViewModelProvider(requireActivity())[WordViewModel::class.java]

        val slovakTextView = view.findViewById<TextView>(R.id.slovak_word)
        val nextButton = view.findViewById<Button>(R.id.next_word_button)

        wordViewModel.currentSlovakWord.observe(viewLifecycleOwner) { slovakWord ->
            slovakTextView.text = slovakWord
        }

        nextButton.setOnClickListener {
            wordViewModel.nextWord()
        }
    }
}
