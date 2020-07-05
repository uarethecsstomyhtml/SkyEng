package com.example.android.dictionary.presentation.ui.dictionary

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.android.dictionary.R
import com.example.android.dictionary.presentation.ui.dictionary.DictionaryFragmentDirections.Companion.actionDictionaryFragmentToDictionaryDetailsFragment
import com.example.android.ui_components.ResultState
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_dictionary.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class DictionaryFragment : Fragment(R.layout.fragment_dictionary), View.OnClickListener {

    private val viewModel: DictionaryViewModel by viewModel()
    private lateinit var adapter: GroupAdapter<GroupieViewHolder>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.i("onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        btnSend.setOnClickListener(this)
        viewModel.dictionary.observe(viewLifecycleOwner, Observer(::onChangeDictionary))
        viewModel.dictionaryClick.observe(viewLifecycleOwner, Observer(::onChangeDictionaryClick))
    }

    private fun setupAdapter() {
        Timber.i("setupAdapter")
        adapter = GroupAdapter()
        adapter.setOnItemClickListener { item, _ ->
            val id = (item as DictionaryItem).dictionaryUi.id
            viewModel.onItemClick(id)
        }
        rvDictionary.adapter = adapter
    }

    private fun loadDictionaryBySearch(word: String = "hello") {
        Timber.i("loadDictionaryBySearch, word = $word")
        viewModel.loadDictionaryBySearch(word)
    }

    private fun onChangeDictionary(result: ResultState<List<DictionaryUi>>) {
        Timber.i("onChangeDictionary, result = $result")
        if (result is ResultState.Success) handleSuccess(result.data)
    }

    private fun handleSuccess(dictionary: List<DictionaryUi>) {
        adapter.clear()
        for (item in dictionary) adapter.add(DictionaryItem(item))
    }

    private fun onChangeDictionaryClick(id: Long) {
        Timber.i("onChangeDictionaryClick, id = $id")
        val action = actionDictionaryFragmentToDictionaryDetailsFragment(id)
        findNavController().navigate(action)
    }

    override fun onClick(view: View?) {
        if (etWord.text.toString().isBlank()) Toast.makeText(context, "Пустая строка", Toast.LENGTH_LONG).show()
        else loadDictionaryBySearch(etWord.text.toString())
    }

}