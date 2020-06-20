package com.daracul.wordsseacher.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.daracul.wordsseacher.Dependencies
import com.daracul.wordsseacher.R
import com.daracul.wordsseacher.presentation.MainActivity
import com.daracul.wordsseacher.utils.hideSoftKeyboard
import com.daracul.wordsseacher.utils.textChangesDebounce
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
        adapter = WordsAdapter { viewModel.doClick(it) }
        rv_words.adapter = adapter

        search_view.textChangesDebounce({viewModel.searchWord(it)})

        viewModel.wordsLiveData.observe(viewLifecycleOwner,
            Observer {
                adapter.submitList(it?: emptyList())  })
        viewModel.navigateDetailsLiveData.observe(viewLifecycleOwner, Observer {
            val word = it.getContentIfNotHandled()
            word?.let {(requireActivity() as MainActivity).navigateToDetailsFragment(word)}
        })
    }
    

}