package com.daracul.wordsearcher.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.daracul.wordsearcher.R
import com.daracul.wordsearcher.domain.model.Word

class WordsAdapter(private val clickListener: (Word) -> Unit) :
    ListAdapter<Word, WordsViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordsViewHolder {
        return WordsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_word, parent, false)
        )
    }

    override fun onBindViewHolder(holder: WordsViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

}

private object diffCallback : DiffUtil.ItemCallback<Word>() {
    override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean = oldItem == newItem
}
