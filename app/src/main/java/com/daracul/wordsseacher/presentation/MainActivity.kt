package com.daracul.wordsseacher.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.daracul.wordsseacher.R
import com.daracul.wordsseacher.presentation.details.DetailsFragment
import com.daracul.wordsseacher.presentation.main.MainFragment

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

    fun navigateToDetailsFragment(){
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                DetailsFragment()
            )
            .commit()
    }
}