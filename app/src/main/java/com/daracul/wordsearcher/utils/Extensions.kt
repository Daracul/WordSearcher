package com.daracul.wordsearcher.utils

import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.daracul.wordsearcher.R

fun SearchView.textChangesDebounce(
    textChanged: (String) -> Unit,
    onSearchClicked: ((String) -> Unit)? = null,
    delay: Long = 500
) {

    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        private val handler = Handler(Looper.getMainLooper())
        private var runnable: Runnable? = null
        override fun onQueryTextSubmit(query: String?): Boolean {
            onSearchClicked?.let { onSearchClicked(query ?: "") }
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

fun Fragment.loadRoundedImage(url: String, imageView: ImageView) {
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.placeholder)
        .override(
            resources.getDimensionPixelOffset(R.dimen.word_card_width),
            resources.getDimensionPixelOffset(R.dimen.word_card_height)
        )
        .transform(
            CenterCrop(),
            RoundedCorners(resources.getDimensionPixelOffset(R.dimen.word_card_radius))
        )
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(imageView)
}
