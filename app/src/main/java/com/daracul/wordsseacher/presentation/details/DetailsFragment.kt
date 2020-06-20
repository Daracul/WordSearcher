package com.daracul.wordsseacher.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.daracul.wordsseacher.Dependencies
import com.daracul.wordsseacher.R
import com.daracul.wordsseacher.domain.model.Word
import com.daracul.wordsseacher.utils.loadRoundedImage
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment() {
    companion object {

        private const val CONST_WORD = "const.word"

        fun create(word: Word?): DetailsFragment {
            return DetailsFragment().apply {
                arguments = bundleOf(CONST_WORD to word)
            }
        }
    }

    private lateinit var viewModel: DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val word: Word =
            arguments?.getParcelable(CONST_WORD) ?: throw IllegalArgumentException("can't find arg")
        val factory = Dependencies.createDetailsViewModelFactory(word)
        viewModel = ViewModelProvider(this, factory).get(DetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.wordLiveData.observe(viewLifecycleOwner, Observer {
            word -> word?.let {
            text_view_word.text = it.name
            text_view_transcript.text = it.transcription
            text_view_translation.text = it.translation
            text_view_headline.visibility = if (it.otherTranslations.isEmpty()) View.GONE else View.VISIBLE
            text_view_others.text = it.otherTranslations.joinToString("; ")
            loadRoundedImage(it.imageUrl, imageView)
        }
        })
    }
}