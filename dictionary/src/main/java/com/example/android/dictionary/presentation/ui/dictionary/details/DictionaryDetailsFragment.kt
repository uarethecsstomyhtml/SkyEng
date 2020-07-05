package com.example.android.dictionary.presentation.ui.dictionary.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.android.dictionary.R
import com.example.android.ui_components.ResultState
import com.example.android.ui_components.loadImg
import kotlinx.android.synthetic.main.fragment_dictionary_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class DictionaryDetailsFragment : Fragment(R.layout.fragment_dictionary_details) {

    private val args: DictionaryDetailsFragmentArgs by navArgs()
    private val viewModel: DictionaryDetailsViewModel by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.i("onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        loadDictionaryInfo()
        viewModel.dictionaryInfo.observe(viewLifecycleOwner, Observer(::onChangeDictionaryInfo))
    }

    private fun loadDictionaryInfo() {
        Timber.i("loadDictionaryInfo")
        viewModel.loadDictionaryInfo(args.id)
    }

    private fun onChangeDictionaryInfo(result: ResultState<List<DictionaryInfoUi>>) {
        Timber.i("onChangeDictionaryInfo, result = $result")
        if (result is ResultState.Success && result.data.isNotEmpty()) {
            tvWord.text = result.data[0].text
            tvTranslation.text = result.data[0].translation.text
            imgWord.loadImg(result.data[0].images[0].url)
        }
    }

}