package com.example.languagelearner

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.languagelearner.ui.theme.LanguageLearnerTheme

class MainActivity : AppCompatActivity() {
    private lateinit var wordRepository: WordRepository
    private lateinit var wordViewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wordRepository = WordRepository(context = this)
        wordViewModel = ViewModelProvider(this)[WordViewModel::class.java]

        //slová zo slovenského súboru
        val slovakWords = wordRepository.loadWordsFromFile(R.raw.slovak)
        wordViewModel.setWords(slovakWords, emptyList())

        // Spinner
        val spinner = findViewById<Spinner>(R.id.language_selector)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedLanguageFile = when (position) {
                    0 -> R.raw.english
                    1 -> R.raw.german
                    2 -> R.raw.spanish
                    3 -> R.raw.klingon
                    else -> R.raw.english
                }

                // cudzie slová pre ForeignFragment
                val foreignWords = wordRepository.loadWordsFromFile(selectedLanguageFile)
                wordViewModel.setWords(slovakWords, foreignWords)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }
}
