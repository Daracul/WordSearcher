package com.daracul.wordsearcher.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.daracul.wordsearcher.R
import com.daracul.wordsearcher.domain.model.Word
import com.daracul.wordsearcher.presentation.details.DetailsFragment
import com.daracul.wordsearcher.presentation.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(
                    R.id.container,
                    MainFragment()
                )
                .commit()
        }
    }

    fun navigateToDetailsFragment(word: Word?) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                DetailsFragment.create(word)
            )
            .addToBackStack(null)
            .commit()
    }
}