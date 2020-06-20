package com.daracul.wordsseacher.utils

import android.os.Handler
import android.os.Looper
import androidx.appcompat.widget.SearchView

fun SearchView.textChangesDebounce(textChanged: (String) -> Unit, delay: Long = 600) {

    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        private val handler = Handler(Looper.getMainLooper())
        private var runnable: Runnable? = null
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            runnable?.let { handler.removeCallbacks(it) }
            runnable = Runnable { textChanged(newText ?: "") }
            runnable?.let { handler.postDelayed(it, delay) }
            return true
        }
    })
}