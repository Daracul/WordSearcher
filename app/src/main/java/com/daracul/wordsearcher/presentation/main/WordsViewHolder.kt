package com.daracul.wordsearcher.presentation.main

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.daracul.wordsearcher.domain.model.Word
import kotlinx.android.synthetic.main.item_word.view.*

class WordsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(word: Word, clickListener: (Word) -> Unit) {
        itemView.text_view_word.text = word.name
        itemView.setOnClickListener { clickListener(word) }
    }
}