package com.daracul.wordsseacher.utils

import android.app.Activity
import android.os.Handler
import android.os.Looper
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.daracul.wordsseacher.R

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

fun Fragment.loadRoundedImage(url:String, imageView: ImageView){
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.placeholder)
        .override(
            resources.getDimensionPixelOffset(R.dimen.word_card_width),
            resources.getDimensionPixelOffset(R.dimen.word_card_height)
        )
        .transform(CenterCrop(), RoundedCorners(resources.getDimensionPixelOffset(R.dimen.word_card_radius)))
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(imageView)
}
