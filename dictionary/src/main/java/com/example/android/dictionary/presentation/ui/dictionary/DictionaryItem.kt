package com.example.android.dictionary.presentation.ui.dictionary

import com.example.android.dictionary.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_dictionary.view.*


class DictionaryItem(val dictionaryUi: DictionaryUi) : Item() {

    override fun getLayout() = R.layout.item_dictionary

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        with(viewHolder.itemView) {
            dictionaryWord.text = dictionaryUi.text
            dictionaryTranslation.text = dictionaryUi.meanings[0].translation.text
        }
    }
}
