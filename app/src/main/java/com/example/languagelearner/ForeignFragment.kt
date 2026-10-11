package com.example.languagelearner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider


class ForeignFragment : Fragment(R.layout.fragment_foreign) {
    private lateinit var wordViewModel: WordViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        wordViewModel = ViewModelProvider(requireActivity())[WordViewModel::class.java]

        val foreignTextView = view.findViewById<TextView>(R.id.foreign_word)
        val toggleButton = view.findViewById<Button>(R.id.toggle_word_button)

        wordViewModel.currentForeignWord.observe(viewLifecycleOwner) { foreignWord ->
            foreignTextView.text = foreignWord
        }

        toggleButton.setOnClickListener {
            if (foreignTextView.visibility == View.VISIBLE) {
                foreignTextView.visibility = View.GONE
                toggleButton.text = "Show word"
            } else {
                foreignTextView.visibility = View.VISIBLE
                toggleButton.text = "Hide word"
            }
        }
    }
}

