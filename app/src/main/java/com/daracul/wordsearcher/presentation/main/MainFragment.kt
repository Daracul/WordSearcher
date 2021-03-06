package com.daracul.wordsearcher.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.daracul.wordsearcher.Dependencies
import com.daracul.wordsearcher.R
import com.daracul.wordsearcher.presentation.MainActivity
import com.daracul.wordsearcher.utils.textChangesDebounce
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private val viewModelFactory = Dependencies.viewModelFactory
    private lateinit var adapter: WordsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        adapter = WordsAdapter { viewModel.openDetails(it) }
        rv_words.adapter = adapter

        search_view.textChangesDebounce(
            { viewModel.searchWord(it) },
            { viewModel.tryOpen(it) })

        viewModel.wordsLiveData.observe(viewLifecycleOwner,
            Observer {
                it?.let { adapter.submitList(it) }
            })
        viewModel.navigateDetailsLiveData.observe(viewLifecycleOwner, Observer {
            val word = it.getContentIfNotHandled()
            word?.let { (requireActivity() as MainActivity).navigateToDetailsFragment(word) }
        })
        viewModel.progressLiveData.observe(viewLifecycleOwner, Observer {
            greetings_text.visibility = View.GONE
            progress_bar.visibility = if (it == true) View.VISIBLE else View.INVISIBLE
        })
        viewModel.showErrorLiveData.observe(viewLifecycleOwner, Observer {
            empty_text.visibility = if (it == true) View.VISIBLE else View.GONE
            rv_words.visibility = if (it == true) View.GONE else View.VISIBLE
        })
    }


}